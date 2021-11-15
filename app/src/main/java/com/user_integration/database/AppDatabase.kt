package com.demo.userintegration.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.userintegration.database.model.UsersDetails

@Database(version = 1,entities = [UsersDetails::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDatabaseHelper() : DatabaseHelper
}