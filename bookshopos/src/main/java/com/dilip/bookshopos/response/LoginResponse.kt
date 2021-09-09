package com.bookshop.response

import com.dilip.bookshopos.Model.User


data class LoginResponse(
    val success : Boolean? = null,
    val token : String? = null,
    val data : List<User>? = null,
    val id : String? = null,
    )
