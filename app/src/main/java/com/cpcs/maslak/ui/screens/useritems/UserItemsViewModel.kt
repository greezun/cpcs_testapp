package com.cpcs.maslak.ui.screens.useritems


import androidx.lifecycle.viewModelScope
import com.cpcs.maslak.domain.usecases.GetItemListUseCase
import com.cpcs.maslak.ui.screens.base.BaseViewModel
import com.cpcs.maslak.ui.screens.useritems.contract.UserItemsContract
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the state and events of the User Items screen.
 *
 * This ViewModel handles loading the list of user items, updating the state, and managing navigation
 * based on user actions on the user items screen.
 *
 * @param getItemListUseCase The use case for retrieving the list of user items.
 */
class UserItemsViewModel(
    private val getItemListUseCase: GetItemListUseCase
) : BaseViewModel<UserItemsContract.Event, UserItemsContract.State, UserItemsContract.Navigation>() {

    override fun initState() = UserItemsContract.State(emptyList())

    override fun handleEvent(event: UserItemsContract.Event) {
        when (event) {
            UserItemsContract.Event.ComeBack -> setEffect {
                UserItemsContract.Navigation.NavigationBack
            }

            is UserItemsContract.Event.ViewItem -> {
                setEffect {
                    UserItemsContract.Navigation.NavigationToItemForm(event.value)
                }
            }

            UserItemsContract.Event.Update -> update()
        }
    }

    private fun update() {
        viewModelScope.launch {
            val item = getItemListUseCase()
            setState {
                UserItemsContract.State(item)
            }
        }
    }
}