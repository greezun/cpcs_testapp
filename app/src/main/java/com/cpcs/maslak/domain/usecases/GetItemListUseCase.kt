package com.cpcs.maslak.domain.usecases

import com.cpcs.maslak.data.model.ItemData
import com.cpcs.maslak.domain.repository.ItemsRepository

/**
 * Use case class responsible for retrieving a list of all items.
 *
 * This class supports direct invocation using the `invoke` operator, allowing the use case to be
 * called like a function, e.g., `getItemListUseCase()`, to fetch all items.
 *
 * @param repository The [ItemsRepository] that provides data operations for items.
 */
class GetItemListUseCase(private val repository: ItemsRepository) {

    /**
     * Retrieves a list of all items from the repository.
     *
     * @return A list of [ItemData] objects representing all items in the repository.
     */
    suspend operator fun invoke(): List<ItemData> = repository.getItemsList()
}