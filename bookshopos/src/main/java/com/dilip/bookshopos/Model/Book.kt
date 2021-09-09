package com.dilip.bookshopos.Model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
 @PrimaryKey(autoGenerate = false)
        val _id:String ="",
        val author: String? = null,
        val title: String? = null,
        val publisher: String? = null,
        val bookname: String? = null,
        val published_year: String? = null,
        val photo: String? = null,
        val desc : String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(publisher)
        parcel.writeString(bookname)
        parcel.writeString(published_year)
        parcel.writeString(photo)
        parcel.writeString(desc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

}