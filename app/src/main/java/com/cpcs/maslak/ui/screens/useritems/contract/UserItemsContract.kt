package com.cpcs.maslak.ui.screens.useritems.contract

import com.cpcs.maslak.data.model.ItemData
import com.cpcs.maslak.ui.screens.base.NavEffect
import com.cpcs.maslak.ui.screens.base.ViewEvent
import com.cpcs.maslak.ui.screens.base.ViewState
/**
 * Defines the contract for the User Items feature, including state, events, and navigation effects.
 */
class UserItemsContract {

    /** Represents the state of the item form screen. */
    data class State(
        val itemsList: List<ItemData>
    ) : ViewState

    /** Represents the events that can occur in the item form screen. */
    sealed class Event : ViewEvent {
        data object Update : Event()
        data class ViewItem(val value: Int) : Event()
        data object ComeBack : Event()
    }

    /** Represents the navigation effect */
    sealed class Navigation : NavEffect {
        data object NavigationBack : Navigation()
        data class NavigationToItemForm(val value: Int) : Navigation()
    }
}