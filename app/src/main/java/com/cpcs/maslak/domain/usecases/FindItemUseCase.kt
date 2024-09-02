package com.cpcs.maslak.domain.usecases

import com.cpcs.maslak.data.model.ItemData
import com.cpcs.maslak.domain.repository.ItemsRepository

/**
 * Use case class responsible for finding an item by its unique identifier.
 *
 * This function allows the use case to be called directly like a function, e.g., `findItemUseCase(id)`.
 *
 * @param repository The [ItemsRepository] that provides data operations for items.
 */
class FindItemUseCase(private val repository: ItemsRepository) {

    /**
     * Finds an item in the repository by its unique identifier.
     *
     * @param id The unique identifier of the item to be found.
     * @return The [ItemData] object if found, or null if no item with the specified ID exists.
     */
    suspend operator fun invoke(id: Int): ItemData? {
        return repository.findById(id)
    }
}