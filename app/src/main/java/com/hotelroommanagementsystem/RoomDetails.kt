package com.hotelroommanagementsystem

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hotelroommanagementsystem.Model.Room
import com.hotelroommanagementsystem.api.ServiceBuilder
import com.bumptech.glide.Glide


class RoomDetails : AppCompatActivity() {
private lateinit var roomnums :TextView
private lateinit var roomtypes:TextView
private lateinit var img: ImageView



override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_edit_room)
    roomtypes=findViewById(R.id.roomtype)
    roomnums =findViewById(R.id.roomnum)
    img =findViewById(R.id.photo)



    val intent = intent.getParcelableExtra<Room>("books")
    if(intent!=null)
    {

        val roomnumber= intent!!.roomnum
        val roomtyp = intent!!.roomtype



        roomnums.text=roomnumber
        roomtypes.text=roomtyp


        val imagePath = ServiceBuilder.loadImagePath() + intent!!.photo
        if (!intent.photo.equals("nophoto.png")) {
            Glide.with(this)
                .load(imagePath)
                .fitCenter()
                .into(img)
        }
    }


}
}