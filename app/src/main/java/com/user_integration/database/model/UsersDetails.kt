package com.demo.userintegration.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UsersDetails(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "dob") val dateOfBirth: String,
    @ColumnInfo(name = "password") val password: String,
){
    @PrimaryKey(autoGenerate = true) var primaryKey: Int = 0
}


