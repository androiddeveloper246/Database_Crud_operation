package com.demo.userintegration.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.demo.userintegration.database.AppDatabase
import com.demo.userintegration.database.DatabaseHelper
import com.demo.userintegration.database.model.UsersDetails

class MainActivityRepository {

   private lateinit var database: DatabaseHelper

    fun initializeDatabase(ctx: Context) {
        database = Room.databaseBuilder(
            ctx,
            AppDatabase::class.java, "user_details"
        ).allowMainThreadQueries().build().getDatabaseHelper()
    }

    fun insertUserDetails(usersDetails: UsersDetails) {
        database.insertUserDetails(usersDetails)
    }

    fun updateUserDetails(n:String,e:String,dob:String,pass:String,id:Int) {
        database.updateUserDetails(n,e,dob,pass,id)
    }

    fun deleteUserDetails(usersDetails: UsersDetails) {
        database.deleteUserDetails(usersDetails)
    }

    fun getUserDetails() : LiveData<List<UsersDetails>>{
        return database.getUserDetails()
    }

}