package com.cpcs.maslak.data.di


import androidx.room.Room
import com.cpcs.maslak.data.model.ItemsRepositoryImpl
import com.cpcs.maslak.data.room.AppDatabase
import com.cpcs.maslak.data.room.ItemsDao
import com.cpcs.maslak.domain.repository.ItemsRepository
import com.cpcs.maslak.domain.usecases.DeleteItemUseCase
import com.cpcs.maslak.domain.usecases.FindItemUseCase
import com.cpcs.maslak.domain.usecases.GetItemListUseCase
import com.cpcs.maslak.domain.usecases.SaveItemUseCase
import com.cpcs.maslak.ui.screens.itemform.ItemFormViewModel
import com.cpcs.maslak.ui.screens.useritems.UserItemsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val koinModules = module {

    single {
        Room.databaseBuilder(
            context = get(),
            klass = AppDatabase::class.java,
            name = "data_base"
        ).build()
    }
    single<ItemsDao> { get<AppDatabase>().itemsDao() }
    single<ItemsRepository> { ItemsRepositoryImpl(get()) }
    single { DeleteItemUseCase(get()) }
    single { FindItemUseCase(get()) }
    single { GetItemListUseCase(get()) }
    single { SaveItemUseCase(get()) }

    viewModelOf(::UserItemsViewModel)
    viewModel { (id: Int?) ->
        ItemFormViewModel(
            id = id,
            findItemUseCase = get(),
            saveItemUseCase = get(),
            deleteItemUseCase = get()
        )
    }

}