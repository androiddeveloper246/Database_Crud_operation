package com.demo.userintegration.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.demo.userintegration.database.model.UsersDetails

@Dao
interface DatabaseHelper {

    @Insert
    fun insertUserDetails(userDetails: UsersDetails)

    @Query("SELECT * FROM usersdetails")
    fun getUserDetails(): LiveData<List<UsersDetails>>


    @Query("UPDATE usersdetails SET name =:n,email =:e, dob = :dob,password=:pass WHERE primaryKey = :id")
    fun updateUserDetails(n: String, e: String, dob: String, pass: String, id: Int)

    @Delete
    fun deleteUserDetails(userDetails: UsersDetails)

}