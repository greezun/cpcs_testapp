package com.cpcs.maslak.data.model

import com.cpcs.maslak.data.room.ItemDataEntity
import com.cpcs.maslak.data.room.ItemsDao
import com.cpcs.maslak.domain.repository.ItemsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementation of [ItemsRepository] that uses a [ItemsDao] to perform CRUD operations
 * on item data stored in a local database.
 *
 * @param itemsDao The data access object for performing operations on the item database.
 */
class ItemsRepositoryImpl(private val itemsDao: ItemsDao) : ItemsRepository {

    /**
     * Retrieves a list of items from the database and converts them to domain models.
     *
     * @return a list of [ItemData] objects representing the items in the database.
     */
    override suspend fun getItemsList(): List<ItemData> {
        return withContext(Dispatchers.IO) {
            itemsDao.getItemsList().map { entity ->
                ItemData(
                    id = entity.id,
                    name = entity.name,
                    description = entity.description
                )
            }
        }
    }

    /**
     * Saves an item to the database. If the item has an ID, it updates the existing item;
     * otherwise, it inserts a new item.
     *
     * @param item The [ItemData] object to be saved or updated in the database.
     */
    override suspend fun saveItem(item: ItemData) {
        withContext(Dispatchers.IO) {
            val entity = ItemDataEntity(
                name = item.name,
                description = item.description
            )
            if (item.id != -1) {
                itemsDao.saveItem(entity.copy(id = item.id))
            } else {
                itemsDao.saveItem(entity)
            }
        }
    }

    /**
     * Finds an item by its unique identifier and converts it to a domain model.
     *
     * @param id The unique identifier of the item.
     * @return The [ItemData] object if found, or null if no item with the specified ID exists.
     */
    override suspend fun findById(id: Int): ItemData? {
        val entity = withContext(Dispatchers.IO) { itemsDao.findById(id) } ?: return null
        return ItemData(
            id = entity.id,
            name = entity.name,
            description = entity.description
        )
    }

    /**
     * Deletes an item from the database by its unique identifier.
     *
     * @param id The unique identifier of the item to be deleted.
     */
    override suspend fun delete(id: Int) {
        withContext(Dispatchers.IO) {
            val entity = itemsDao.findById(id)
            entity?.let {
                itemsDao.delete(it)
            }
        }
    }
}
