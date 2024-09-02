package com.cpcs.maslak.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("items")
data class ItemDataEntity(
    @PrimaryKey(true)
    val id: Int = 0,
    val name: String,
    val description: String
)


