package com.example.diskstoreapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "disk")
data class Disk(
    @PrimaryKey var serialnumber: String,
    @ColumnInfo("title") var title: String,
    @ColumnInfo("author") var author: String,
    @ColumnInfo("year") var year: String
)
