package com.hotelroommanagementsystem

import com.hotelroommanagementsystem.Model.Room
import com.hotelroommanagementsystem.Model.User
import com.hotelroommanagementsystem.api.ServiceBuilder
import com.hotelroommanagementsystem.repository.RoomReaderRepository
import com.hotelroommanagementsystem.repository.RoomRepository
import com.hotelroommanagementsystem.repository.UserRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class HotelRoomManagementSyStem {
    private lateinit var UserRepository: UserRepository
    private lateinit var RoomReaderRepository:  RoomReaderRepository
    private lateinit var RoomRepository: RoomRepository

    @Test
    fun checkLoginTest() = runBlocking {
        UserRepository = UserRepository()
        val user = User(
                email = "arbin@gmail.com",
                password = "arbin567"
        )
        val response = UserRepository.checkUser(user)
        val expectedResult = true
        val actualResult = response.success
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun checkRegisterTest() = runBlocking {
        UserRepository = UserRepository()
        val user = User(
                firstname = "Arbin",
                lastname = "Choudhary",
                email = "arbinchy@gmail.com",
                password = "arbin567",
                mobileno = "98489532025"
        )
        val response = UserRepository.registerUser(user)
        val expectedResult = true
        val actualResult = response.success
        assertEquals(expectedResult, actualResult)
    }


    @Test
    fun deleteRoom() = runBlocking {
       UserRepository = UserRepository()
        val userlogin = User(email = "arbin@gmail.com", password = "arbin567")
        ServiceBuilder.token = "Bearer " + UserRepository.checkUser(userlogin).token
        RoomReaderRepository = RoomReaderRepository()
        val id = "607c170b8eb4a909b83afa1c"
        val response = RoomReaderRepository.deleteRoom(id)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }




    @Test
    fun getUserCheck() = runBlocking {
        UserRepository = UserRepository()
        val userlogin = User(email = "arbin@gmail.com", password = "arbin567")
        ServiceBuilder.token = "Bearer " + UserRepository.checkUser(userlogin).token
        val response = UserRepository.getDetails()
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }


    @Test
    fun singleItem() = runBlocking {

        UserRepository = UserRepository()
        val userlogin = User(email = "arbin@gmail.com", password = "arbin567")
        ServiceBuilder.token = "Bearer " + UserRepository.checkUser(userlogin).token
        RoomRepository = RoomRepository()
        val id = "607c170b8eb4a909b83afa1c"
        val response = RoomRepository.singleIteam(id)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }


    @Test
    fun getRoomsReader() = runBlocking {
        UserRepository = UserRepository()
        val userlogin = User(email = "arbin@gmail.com", password = "arbin567")
        ServiceBuilder.token = "Bearer " + UserRepository.checkUser(userlogin).token
        RoomReaderRepository = RoomReaderRepository()
        val response = RoomReaderRepository.getRooms("607c170b8eb4a909b83afa1c")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun insertRoomsReader() = runBlocking {
        UserRepository = UserRepository()
        val userlogin = User(email = "arbin@gmail.com", password = "arbin567")
        ServiceBuilder.token = "Bearer " + UserRepository.checkUser(userlogin).token
        UserRepository = UserRepository()
        val room = Room(
                roomnum = "a1",
                _id = "home", )
        val response = UserRepository.getDetails()
        val expectedResult = true
        val actualResult = response.success
        assertEquals(expectedResult, actualResult)


    }

    @Test
    fun getRooms() = runBlocking {
        UserRepository = UserRepository()
        val userlogin = User(email = "arbin@gmail.com", password = "arbin")
        ServiceBuilder.token = "Bearer " + UserRepository.checkUser(userlogin).token
        RoomReaderRepository = RoomReaderRepository()
        val response = RoomReaderRepository.getRooms("607c170b8eb4a909b83afa1c")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)


    }

    @Test
    fun getRoomsById() = runBlocking {
        UserRepository = UserRepository()
        val userlogin = User(email = "arbin@gmail.com", password = "arbin567")
        ServiceBuilder.token = "Bearer " + UserRepository.checkUser(userlogin).token
        RoomRepository = RoomRepository()
        val response = RoomRepository.getRooms()
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)

    }
}