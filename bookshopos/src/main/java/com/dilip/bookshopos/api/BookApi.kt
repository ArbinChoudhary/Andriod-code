package com.dilip.bookshopos.api



import com.bookshop.response.AddtoCartResponse
import com.bookshop.response.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface BookApi {

    @GET("books/showalls")
    suspend fun getAllProducts(
        @Header("Authorization") token: String
    ): Response<BookResponse>

    @GET("books/single/{id}")
    suspend fun getProductsingleItem(
        @Header("Authorization") token:String,
        @Path("id") id:String
    ):Response<AddtoCartResponse>
}