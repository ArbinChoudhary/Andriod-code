package com.hotelroommanagementsystem.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hotelroommanagementsystem.Model.User

@Dao
interface UserDAO {
    //insert function
    @Insert
    suspend fun registerUser(user:User)

    @Query("Delete from User")
    suspend fun logout()


    @Query("select * from User where email=(:email) and password=(:password)")
    suspend fun checkUser(email: String, password: String): User

    @Delete
    suspend fun deleteUser(user:User)

    @Query("Delete from User")
    suspend fun deleteUser()

    @Query("Select * from User")
    suspend fun  getUsers(): User

}