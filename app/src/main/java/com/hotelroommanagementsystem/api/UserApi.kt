package com.hotelroommanagementsystem.api

import com.hotelroommanagementsystem.Model.User
import com.hotelroommanagementsystem.response.LoginResponse
import com.hotelroommanagementsystem.response.UserProfileResponse
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

        @POST("user/registration")
        suspend fun registerUser(
                @Body user : User
        ) : Response<LoginResponse>

        //Login user
        @POST("user/login")
        suspend fun checkUserLogin(
                @Body user : User
        ): Response<LoginResponse>


        @GET("user/account")
        suspend fun getUser(
                @Header("Authorization") token:String
        ):Response<UserProfileResponse>



}
