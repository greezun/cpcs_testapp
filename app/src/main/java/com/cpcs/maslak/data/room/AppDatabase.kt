package com.cpcs.maslak.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ItemDataEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemsDao(): ItemsDao
}