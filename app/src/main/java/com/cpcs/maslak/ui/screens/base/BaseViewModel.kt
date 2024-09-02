package com.cpcs.maslak.ui.screens.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

interface ViewEvent
interface ViewState
interface NavEffect

/**
 * BaseViewModel is an abstract class that follows the MVI (Model-View-Intent) pattern.
 * It is designed to manage UI state, handle events, and emit effects in a type-safe way.
 *
 * @param UIEvent  the type of events that the ViewModel will handle, which must implement [ViewEvent].
 * @param UIState  the type of states that the ViewModel will manage, which must implement [ViewState].
 * @param UIEffect the type of effects that the ViewModel can emit, which must implement [NavEffect].
 */

abstract class BaseViewModel<UIEvent : ViewEvent, UIState : ViewState, UIEffect : NavEffect> :
    ViewModel() {


    private val initialState by lazy { initState() }

    private val _state: MutableState<UIState> = mutableStateOf(initialState)
    val state: State<UIState> = _state

    private val _event: MutableSharedFlow<UIEvent> = MutableSharedFlow()

    private val _effect = MutableSharedFlow<UIEffect>(replay = 0)
    val effect: SharedFlow<UIEffect> = _effect

    init {
        subscribeToEvents()
    }

    /**
     * Subscribes to the events collected from the _event flow and handles them.
     */
    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect {
                handleEvent(it)
            }
        }
    }

    /**
     * Initializes the initial state for the ViewModel.
     *
     * @return the initial state of type UIState.
     */
    abstract fun initState(): UIState

    /**
     * Handles the incoming events from the UI.
     *
     * @param event the event to handle, of type UIEvent.
     */
    abstract fun handleEvent(event: UIEvent)

    /**
     * Updates the UI state with the provided reducer function.
     *
     * @param reducer a function that takes the current state and returns a new state.
     */
    protected fun setState(reducer: UIState.() -> UIState) {
        _state.value = state.value.reducer()
    }

    /**
     * Sets the navigation effect to be emitted.
     *
     * @param builder a function that returns the effect of type UIEffect.
     */
    protected fun setEffect(builder: () -> UIEffect) {
        viewModelScope.launch { _effect.emit(builder()) }
    }
}