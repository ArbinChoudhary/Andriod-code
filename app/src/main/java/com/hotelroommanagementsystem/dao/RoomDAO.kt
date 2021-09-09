package com.hotelroommanagementsystem.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hotelroommanagementsystem.Model.Room


@Dao
interface RoomDAO {

    @Query("Delete from room")
    suspend fun deleteRoom()

    @Insert
    suspend fun insertRooms(room: MutableList<Room>?)



    @Insert
    suspend fun registerProduct(room: Room)


    @Delete
    suspend fun deleteRooms(product: Room)

    @Query("Select * from room")
    suspend fun  getProduct(): Room





}