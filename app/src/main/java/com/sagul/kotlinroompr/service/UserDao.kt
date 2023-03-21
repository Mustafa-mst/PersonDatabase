package com.sagul.kotlinroompr.service

import androidx.room.*
@Dao
interface UserDao {
    @Query(value = "SELECT * FROM users ORDER BY id ASC")
   suspend fun getAll():List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(user:User)

    @Update
    suspend fun updateUser(user:User)

    @Delete
    suspend fun deleteUser(user:User)
}