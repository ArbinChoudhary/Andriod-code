package com.dilip.bookshopos.repository


import com.bookshop.response.LoginResponse
import com.bookshop.response.UserProfileResponse
import com.dilip.bookshopos.Model.User
import com.dilip.bookshopos.api.MyApiRequest
import com.dilip.bookshopos.api.ServiceBuilder
import com.dilip.bookshopos.api.UserApi

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
