package com.hotelroommanagementsystem.repository

import com.hotelroommanagementsystem.api.MyApiRequest
import com.hotelroommanagementsystem.api.ServiceBuilder
import com.hotelroommanagementsystem.api.UserApi
import com.hotelroommanagementsystem.Model.User
import com.hotelroommanagementsystem.response.LoginResponse
import com.hotelroommanagementsystem.response.UserProfileResponse

class UserRepository: MyApiRequest() {
    val myApi = ServiceBuilder.buildService(UserApi::class.java)
    suspend fun registerUser(user: User): LoginResponse {
        return apiRequest {
            myApi.registerUser(user)
        }
    }
    suspend fun checkUser(user: User):LoginResponse{
        return apiRequest {
            myApi.checkUserLogin(user)
        }
    }

    suspend fun getDetails():UserProfileResponse {
        return apiRequest {
            myApi.getUser(ServiceBuilder.token!!)
        }
    }



}
