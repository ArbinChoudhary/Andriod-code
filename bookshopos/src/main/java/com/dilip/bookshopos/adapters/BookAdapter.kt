package com.dilip.bookshopos.adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dilip.bookshopos.Model.Book
import com.dilip.bookshopos.R
import com.dilip.bookshopos.BookActivity
import com.dilip.bookshopos.api.ServiceBuilder


class BookAdapter (val context: Context, val lstBooks: MutableList<Book>)
    : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.img)
        val bookname: TextView = view.findViewById(R.id.bookname)
        val title: TextView = view.findViewById(R.id.title)
//        val author: TextView = view.findViewById(R.id.author)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_recycler_view, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val books = lstBooks[position]
        holder.bookname.text = books.bookname
        holder.title.text = books.title
//        holder.author.text = books.author
        holder.img.setOnClickListener() {
            val intent = Intent(context, BookActivity::class.java)
            intent.putExtra("books",books)
            context.startActivity(intent)
        }

        Glide.with(context).load(books.photo).into(holder.img)
        val imagePath = ServiceBuilder.loadImagePath() + books.photo
        if (!books.photo.equals("no-photo.jpg")) {
            Glide.with(context)
                .load(imagePath)
                .fitCenter()
                .into(holder.img)
        }
    }

    override fun getItemCount(): Int {
        return lstBooks.size
    }


}