package com.hotelroommanagementsystem.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hotelroommanagementsystem.Model.User
import com.hotelroommanagementsystem.dao.RoomDAO
import com.hotelroommanagementsystem.dao.UserDAO


@Database(
    entities = [(User::class),(Room::class)],
    version = 5
)
abstract class UserDB : RoomDatabase() {

    abstract fun getUserDAO() : UserDAO
    abstract  fun getRoomDAO(): RoomDAO




    companion object{
        @Volatile
        private var instance : UserDB? = null
        fun getInstance(context : Context):UserDB{
            if (instance == null){
                synchronized(UserDB::class){
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }
        private fun buildDatabase(context: Context)=
                Room.databaseBuilder(
                        context.applicationContext,
                        UserDB::class.java,
                        "UserDB"
                ).build()
    }

}