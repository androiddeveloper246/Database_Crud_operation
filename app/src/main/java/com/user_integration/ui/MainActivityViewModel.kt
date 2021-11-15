package com.demo.userintegration.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.userintegration.database.model.UsersDetails

class MainActivityViewModel : ViewModel(){

    private lateinit var mainActivityRepository : MainActivityRepository
    var isForUpdate = false
    lateinit var updateUserDetails : UsersDetails

    fun modelInItDatabase(ctx : Context){
        mainActivityRepository = MainActivityRepository()
        mainActivityRepository.initializeDatabase(ctx)
    }

    fun modelGetUsersDetails() : LiveData<List<UsersDetails>>{
        var user : LiveData<List<UsersDetails>>  = mainActivityRepository.getUserDetails()
        return user
    }

    fun modelInsertDetails(usersDetails: UsersDetails){
        mainActivityRepository.insertUserDetails(usersDetails)
    }

    fun modelDeleteDetails(usersDetails: UsersDetails){
        mainActivityRepository.deleteUserDetails(usersDetails)
    }

    fun modelUpdateUserDetails(n:String,e:String,dob:String,pass:String,id:Int){
        mainActivityRepository.updateUserDetails(n,e,dob,pass,id)
    }

}