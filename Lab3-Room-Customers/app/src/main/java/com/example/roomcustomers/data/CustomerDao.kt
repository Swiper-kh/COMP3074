package com.example.roomcustomers.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/** Data Access Object: the only database operations the app is allowed to use. */
@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(customer: Customer)

    @Delete
    suspend fun delete(customer: Customer)

    // Flow -> Room re-emits the list every time the customers table changes.
    @Query("SELECT * FROM customers ORDER BY id")
    fun getAllCustomers(): Flow<List<Customer>>

    @Query(
        "SELECT * FROM customers " +
            "WHERE name LIKE '%' || :searchText || '%' " +
            "ORDER BY name"
    )
    fun searchCustomers(searchText: String): Flow<List<Customer>>
}
