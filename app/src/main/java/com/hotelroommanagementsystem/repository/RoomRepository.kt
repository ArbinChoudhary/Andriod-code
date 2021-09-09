package com.hotelroommanagementsystem.repository

import com.hotelroommanagementsystem.api.RoomApi
import com.hotelroommanagementsystem.api.MyApiRequest
import com.hotelroommanagementsystem.api.ServiceBuilder
import com.hotelroommanagementsystem.response.RoomResponse

class RoomRepository : MyApiRequest() {
    private val roomapi = ServiceBuilder.buildService(RoomApi::class.java)

    suspend fun getBooks(): RoomResponse {
        return apiRequest {
            roomapi.getAllProducts(ServiceBuilder.token!!)
        }
    }


}