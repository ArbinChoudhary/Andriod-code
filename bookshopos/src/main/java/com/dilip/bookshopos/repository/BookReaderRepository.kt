package com.dilip.bookshopos.repository

import com.bookshop.response.BookReaderResponse
import com.dilip.bookshop.response.DeleteBookReaderResponse
import com.dilip.bookshopos.Model.MycartBook
import com.dilip.bookshopos.api.AddtoCartApi
import com.dilip.bookshopos.api.MyApiRequest
import com.dilip.bookshopos.api.ServiceBuilder


class BookReaderRepository : MyApiRequest(){


 val book= ServiceBuilder.buildService(AddtoCartApi::class.java)


suspend fun getBooks(id: String): BookReaderResponse {
    return apiRequest {
        book.getBook(ServiceBuilder.token!!,id)
    }
}
suspend fun insertBook(mycartBook: MycartBook): BookReaderResponse{
    return  apiRequest {
        book.insertBook(ServiceBuilder.token!!,mycartBook)
    }
}
suspend fun deleteBook(id: String): DeleteBookReaderResponse {
    return apiRequest {
        book.deleteBook(ServiceBuilder.token!!,id)
    }
}

}