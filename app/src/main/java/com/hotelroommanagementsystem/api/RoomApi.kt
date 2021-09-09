package com.hotelroommanagementsystem.api


import com.hotelroommanagementsystem.response.RoomResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RoomApi {

    @GET("rooms/showalls")
    suspend fun getAllProducts(
        @Header("Authorization") token: String
    ): Response<RoomResponse>

    @GET("rooms/single/{id}")
    suspend fun getProductsingleItem(
        @Header("Authorization") token:String,
        @Path("id") id:String
    )
}