package com.cpcs.maslak.domain.usecases

import com.cpcs.maslak.data.model.ItemData
import com.cpcs.maslak.domain.repository.ItemsRepository

/**
 * Use case class responsible for saving an item to the repository.
 *
 * This class supports direct invocation using the `invoke` operator, allowing the use case to be
 * called like a function, e.g., `saveItemUseCase(item)`, to save an item.
 *
 * @param repository The [ItemsRepository] that provides data operations for items.
 */
class SaveItemUseCase(private val repository: ItemsRepository) {

    /**
     * Saves the provided item to the repository.
     *
     * @param item The [ItemData] object to be saved in the repository.
     */
    suspend operator fun invoke(item: ItemData) {
        repository.saveItem(item)
    }
}