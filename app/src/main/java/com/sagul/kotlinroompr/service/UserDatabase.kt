package com.sagul.kotlinroompr.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [User::class], version = 1)
abstract class UserDatabase:RoomDatabase() {
    abstract fun getDao():UserDao
    companion object{
       @Volatile private var instance:UserDatabase?=null
        fun databaseInstance(context: Context)= instance?: synchronized(this){
            instance?:Room.databaseBuilder(context.applicationContext,UserDatabase::class.java,"UserDatabase").build().also {
                instance=it
            }
        }
    }

}