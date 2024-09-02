package com.cpcs.maslak.domain.usecases

import com.cpcs.maslak.domain.repository.ItemsRepository

/**
 * Use case class responsible for deleting an item by its unique identifier.
 *
 * This class supports direct invocation using the `invoke` operator, allowing the use case to be
 * called like a function, e.g., `deleteItemUseCase(id)`, to delete an item.
 *
 * @param repository The [ItemsRepository] that provides data operations for items.
 */
class DeleteItemUseCase(private val repository: ItemsRepository) {

    /**
     * Invokes the use case to delete an item from the repository.
     *
     * @param id The unique identifier of the item to be deleted.
     */
    suspend operator fun invoke(id: Int) {
        repository.delete(id)
    }
}