package com.cpcs.maslak.ui.screens.itemform


import androidx.lifecycle.viewModelScope
import com.cpcs.maslak.R
import com.cpcs.maslak.data.model.ItemData
import com.cpcs.maslak.domain.usecases.DeleteItemUseCase
import com.cpcs.maslak.domain.usecases.FindItemUseCase
import com.cpcs.maslak.domain.usecases.SaveItemUseCase
import com.cpcs.maslak.ui.screens.base.BaseViewModel
import com.cpcs.maslak.ui.screens.itemform.contract.ItemFormContract
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the state and events of the Item Form screen.
 *
 * This ViewModel handles user interactions on the item form, including updating,
 * saving, and deleting items, as well as navigating back.
 *
 * @param id The optional ID of the item being edited; null if creating a new item.
 * @param findItemUseCase The use case for finding an item by its ID.
 * @param saveItemUseCase The use case for saving an item.
 * @param deleteItemUseCase The use case for deleting an item.
 */
class ItemFormViewModel(
    private val id: Int? = null,
    private val findItemUseCase: FindItemUseCase,
    private val saveItemUseCase: SaveItemUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
) : BaseViewModel<ItemFormContract.Event, ItemFormContract.State, ItemFormContract.NavigationBack>() {
    override fun initState() = ItemFormContract.State(
        screenNameRes = R.string.add_item,
        itemName = "",
        itemNameFieldError = false,
        itemDescription = "",
        isEditMode = false
    )

    override fun handleEvent(event: ItemFormContract.Event) {
        when (event) {
            ItemFormContract.Event.ComeBack -> setEffect { ItemFormContract.NavigationBack }
            ItemFormContract.Event.Delete -> deleteItem()
            ItemFormContract.Event.Save -> saveItem()
            is ItemFormContract.Event.SetItemDescription -> setState { copy(itemDescription = event.value) }
            is ItemFormContract.Event.SetItemName -> setState { copy(itemName = event.value) }
            ItemFormContract.Event.Update -> updateState()
        }
    }

    private fun updateState() {
        id?.let { id ->
            viewModelScope.launch {
                val item = findItemUseCase(id)
                item?.let {
                    setState {
                        copy(
                            screenNameRes = R.string.edit,
                            itemName = it.name,
                            itemDescription = it.description,
                            isEditMode = true
                        )
                    }
                }

            }
        }

    }

    private fun saveItem() {
        viewModelScope.launch {
            if (state.value.itemName.isNotBlank()) {
                setState { copy(itemNameFieldError = false) }
                saveItemUseCase(
                    ItemData(
                        id = id ?: -1,
                        name = state.value.itemName,
                        description = state.value.itemDescription
                    )
                )
                setEffect { ItemFormContract.NavigationBack }
            } else {
                setState { copy(itemNameFieldError = true) }
            }

        }
    }

    private fun deleteItem() {
        id?.let {
            viewModelScope.launch {
                deleteItemUseCase(id)
            }
            setEffect { ItemFormContract.NavigationBack }
        }
    }
}