package com.example.diskstoreapp.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.diskstoreapp.model.Customer

@Dao
interface CustomerDao {
    @Query("SELECT * from customer")
    fun list(): List<Customer>
}