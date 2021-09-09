package com.dilip.bookshopos.repository

import com.bookshop.response.AddtoCartResponse
import com.bookshop.response.BookResponse
import com.dilip.bookshopos.api.BookApi
import com.dilip.bookshopos.api.MyApiRequest
import com.dilip.bookshopos.api.ServiceBuilder


class BookRepository : MyApiRequest() {
    private val bookapi = ServiceBuilder.buildService(BookApi::class.java)

    suspend fun getProduct(): BookResponse {
        return apiRequest {
            bookapi.getAllProducts(ServiceBuilder.token!!)
        }
    }


    suspend fun singleIteam(id:String): AddtoCartResponse {
        return apiRequest {
            bookapi.getProductsingleItem(ServiceBuilder.token!!,id)
        }
    }

}