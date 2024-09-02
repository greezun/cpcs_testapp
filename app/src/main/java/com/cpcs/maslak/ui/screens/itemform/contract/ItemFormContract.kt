package com.cpcs.maslak.ui.screens.itemform.contract

import com.cpcs.maslak.ui.screens.base.NavEffect
import com.cpcs.maslak.ui.screens.base.ViewEvent
import com.cpcs.maslak.ui.screens.base.ViewState

/**
 * Defines the contract for the Item Form feature, including state, events, and navigation effects.
 */
class ItemFormContract {

    /** Represents the state of the item form screen. */
    data class State(
        val screenNameRes: Int,
        val itemName: String,
        val itemNameFieldError: Boolean,
        val itemDescription: String,
        val isEditMode: Boolean
    ) : ViewState

    /** Represents the events that can occur in the item form screen. */
    sealed class Event : ViewEvent {
        data object Update : Event()
        data class SetItemName(val value: String) : Event()
        data class SetItemDescription(val value: String) : Event()
        data object Save : Event()
        data object Delete : Event()
        data object ComeBack : Event()
    }

    /** Represents the navigation effect to go back. */
    data object NavigationBack : NavEffect
}