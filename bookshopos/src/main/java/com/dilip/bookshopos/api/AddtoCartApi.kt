package com.dilip.bookshopos.api


import com.bookshop.response.BookReaderResponse

import com.dilip.bookshop.response.DeleteBookReaderResponse
import com.dilip.bookshopos.Model.MycartBook
import retrofit2.Response
import retrofit2.http.*


interface AddtoCartApi {
    @POST("cartbook/item")
    suspend fun insertBook(
        @Header("Authorization") token: String,
        @Body mycartBook: MycartBook


    ) : Response<BookReaderResponse>


    @GET("cartbook/showall/{id}")
    suspend fun getBook(
        @Header("Authorization") token: String,
        @Path("id") id:String



    ): Response<BookReaderResponse>

    @DELETE("cartbook/delete/{id}")
    suspend fun deleteBook(
        @Header("Authorization") token:String,
        @Path("id") id:String
    ):Response<DeleteBookReaderResponse>

}