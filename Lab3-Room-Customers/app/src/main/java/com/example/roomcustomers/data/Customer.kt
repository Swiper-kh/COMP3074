package com.example.roomcustomers.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity -> the "customers" table in SQLite.
 * Each Customer object is one row.
 */
@Entity(tableName = "customers")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val age: Int,
    val isActive: Boolean
)
