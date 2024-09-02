package com.cpcs.maslak

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cpcs.maslak.data.room.AppDatabase
import com.cpcs.maslak.data.room.ItemDataEntity
import com.cpcs.maslak.data.room.ItemsDao
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TestRoomDatabase {
    private lateinit var itemsDao: ItemsDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        itemsDao = db.itemsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun addAndFindItemById() {
        val item = ItemDataEntity(
            id = 2,
            name = "My test item",
            description = "Test item description"
        )
        itemsDao.saveItem(item)
        val iemByID = itemsDao.findById(2)
        assertEquals(iemByID?.name, item.name)

    }

    @Test
    @Throws(Exception::class)
    fun getItemsList() {
        val item = ItemDataEntity(
            id = 3,
            name = "My test item",
            description = "Test item description"
        )
        itemsDao.saveItem(item)
        val itemsList = itemsDao.getItemsList()
        assert(itemsList.isNotEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun deleteItem() {
        val item = ItemDataEntity(
            id = 4,
            name = "My test item",
            description = "Test item description"
        )
        itemsDao.saveItem(item)
        itemsDao.delete(item)
        val itemsList = itemsDao.getItemsList()
        assert(itemsList.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun updateItem() {
        val item = ItemDataEntity(
            id = 5,
            name = "My test item_1",
            description = "Test item description_1"
        )
        itemsDao.saveItem(item)
        val itemByID = itemsDao.findById(4)

        val newItem = itemByID?.copy(
            name = "My test item_2",
            description = "Test item description_2"
        )
        newItem?.let { itemsDao.saveItem(newItem) }
        val newItemById = itemsDao.findById(4)
        assertEquals(newItem, newItemById)
    }
}