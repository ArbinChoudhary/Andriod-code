package com.hotelroommanagementsystem.Model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Room(
 @PrimaryKey(autoGenerate = false)
        val _id:String ="",
        val roomnum: String? = null,
        val roomtype: String? = null,
        val photo: String? = null,
        val desc : String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())
             {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(roomnum)
        parcel.writeString(roomtype)
        parcel.writeString(photo)
        parcel.writeString(desc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Room> {
        override fun createFromParcel(parcel: Parcel): Room {
            return Room(parcel)
        }

        override fun newArray(size: Int): Array<Room?> {
            return arrayOfNulls(size)
        }
    }

}