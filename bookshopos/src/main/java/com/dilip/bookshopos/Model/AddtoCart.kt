package com.dilip.bookshopos.Model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class AddtoCart(
    @PrimaryKey(autoGenerate = true)
    val bid :Int=0,
    val _id: String? = null,
    val author: String? = null,
    val title: String? = null,
    val publisher: String? = null,
    val bookname: String? = null,
    val published_year: String? = null,
    val photo: String? = null,
    val desc : String? = null,
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(bid)
        parcel.writeString(_id)
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeValue(publisher)
        parcel.writeString(bookname)
        parcel.writeString(published_year)
        parcel.writeString(photo)
        parcel.writeString(desc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AddtoCart> {
        override fun createFromParcel(parcel: Parcel): AddtoCart {
            return AddtoCart(parcel)
        }

        override fun newArray(size: Int): Array<AddtoCart?> {
            return arrayOfNulls(size)
        }
    }

}