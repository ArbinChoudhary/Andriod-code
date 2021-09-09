package com.dilip.bookshopos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dilip.bookshopos.Model.Book
import com.dilip.bookshopos.R
import com.dilip.bookshopos.adapters.BookAdapter
import com.dilip.bookshopos.db.UserDB
import com.dilip.bookshopos.repository.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookActivity : AppCompatActivity() {

    private lateinit var book_recycle : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        book_recycle = findViewById(R.id.book_recycle)
        CoroutineScope(Dispatchers.IO).launch {
            val repository = BookRepository()
            val response = repository.getProduct()
            if(response.success==true){
                val lst = response.data
                withContext(Dispatchers.Main){
                    val adapter = BookAdapter(this@BookActivity,lst as MutableList<Book>)
                    book_recycle.adapter = adapter
                    book_recycle.layoutManager = LinearLayoutManager(this@BookActivity)
                }
            }

        }
    }
}