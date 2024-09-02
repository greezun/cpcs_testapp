package com.cpcs.maslak.domain.repository

import com.cpcs.maslak.data.model.ItemData

/**
 * ItemsRepository is an interface that defines the contract for data operations
 * related to items. It provides methods for retrieving, saving, finding, and deleting items.
 */
interface ItemsRepository {

    /**
     * Retrieves the list of all items.
     *
     * @return a list of [ItemData] objects.
     */
    suspend fun getItemsList(): List<ItemData>

    /**
     * Saves the specified item.
     *
     * @param item the [ItemData] object to be saved.
     */
    suspend fun saveItem(item: ItemData)

    /**
     * Finds an item by its unique identifier.
     *
     * @param id the unique identifier of the item.
     * @return the [ItemData] object if found, or null if no item with the specified id exists.
     */
    suspend fun findById(id: Int): ItemData?

    /**
     * Deletes an item by its unique identifier.
     *
     * @param id the unique identifier of the item to be deleted.
     */
    suspend fun delete(id: Int)
}
