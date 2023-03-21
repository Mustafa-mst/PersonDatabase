package com.sagul.kotlinroompr.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sagul.kotlinroompr.service.User
import com.sagul.kotlinroompr.service.UserDatabase
import kotlinx.coroutines.launch

class LocalViewModel(app:Application):baseViewModel(app) {
    var userList=MutableLiveData<List<User>>()
    var depo=UserDatabase.databaseInstance(app).getDao()
    fun insertData(user:User){
        launch {
            depo.insertData(user)
        }

    }
    fun getAll(){
        launch {
           userList.postValue(depo.getAll())
        }
    }
    fun deleteData(user:User){
        launch {
            depo.deleteUser(user)
        }
    }
    fun updateData(user:User){
        launch {
            depo.updateUser(user)
        }
    }
}