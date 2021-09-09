package com.bookshop.response

import com.dilip.bookshopos.Model.AddtoCart
import com.dilip.bookshopos.Model.MycartBook


class BookReaderResponse {
    val success : Boolean? = null
    val token : String? = null
    val id: String?=null
    val data :List<MycartBook>?=null
    val datas :List<AddtoCart>?=null
}