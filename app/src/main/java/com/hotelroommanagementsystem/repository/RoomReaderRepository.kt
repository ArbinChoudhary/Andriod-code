package com.hotelroommanagementsystem.repository

import com.hotelroommanagementsystem.api.MyApiRequest
import com.hotelroommanagementsystem.api.ServiceBuilder
import com.hotelroommanagementsystem.response.RoomReaderResponse
import com.hotelroommanagementsystem.response.DeleteRoomReaderResponse

class RoomReaderRepository : MyApiRequest(){



suspend fun getBooks(id: String): RoomReaderResponse {
    return apiRequest {
        room.getRoom(ServiceBuilder.token!!,id)
    }
}

suspend fun deleteBook(id: String): DeleteRoomReaderResponse {
    return apiRequest {
        room.deleteRoom(ServiceBuilder.token!!,id)
    }
}

}