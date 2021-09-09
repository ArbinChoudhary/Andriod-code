package com.hotelroommanagementsystem.Model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
        @PrimaryKey
        val _id: String ="",
        val firstname: String? = null,
        val lastname: String? = null,
        val email : String? = null,
        val password : String? = null,
        val mobileno : String? =null,
        val photo : String? = null,


): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(firstname)
        parcel.writeString(lastname)
        parcel.writeString(email)
        parcel.writeString(password)
        parcel.writeString(mobileno)

    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }
        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
