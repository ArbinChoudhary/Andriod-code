package com.hotelroommanagementsystem.response

import com.hotelroommanagementsystem.Model.User


data class UserProfileResponse(
        val success: Boolean? = null,
        val data : User?=null,
        val id : String?=""

)