package com.example.diskstoreapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.diskstoreapp.database.dao.CustomerDao
import com.example.diskstoreapp.database.dao.DiskDao
import com.example.diskstoreapp.model.Customer
import com.example.diskstoreapp.model.Disk

@Database(entities = [Disk::class, Customer::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        val DATABASE_NAME = "diskstore"
    }
    abstract fun diskDao(): DiskDao
    abstract fun customerDao(): CustomerDao
}