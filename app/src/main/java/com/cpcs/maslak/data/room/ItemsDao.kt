package com.cpcs.maslak.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemsDao {

    @Query("SELECT * FROM items")
    fun getItemsList(): List<ItemDataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveItem(item: ItemDataEntity)

    @Query("SELECT * FROM items WHERE id = :id")
    fun findById(id: Int): ItemDataEntity?

    @Delete
    fun delete(item: ItemDataEntity)
}