package com.bookshop.response

import com.dilip.bookshopos.Model.User


data class UserProfileResponse(
        val success: Boolean? = null,
        val data : User?=null,
        val id : String?=""

)