package com.hotelroommanagementsystem.adapters

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.RecyclerView
import com.hotelroommanagementsystem.RoomDetails
import com.hotelroommanagementsystem.DashboardActivity
import com.hotelroommanagementsystem.Notification.notifications
import com.hotelroommanagementsystem.R
import com.hotelroommanagementsystem.api.ServiceBuilder
import com.hotelroommanagementsystem.Model.Room
import com.bumptech.glide.Glide


class RoomAdapter (val context: Context, val lstRooms: MutableList<Room>)
    : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.img)
        val roomnum: TextView = view.findViewById(R.id.roomnum)
        val roomtype: TextView = view.findViewById(R.id.roomtype)
        val views: TextView = view.findViewById(R.id.views)

//        val show: Button = view.findViewById(R.id.show)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_room_layout, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val rooms = lstRooms[position]
        holder.roomnum.text = rooms.roomnum
        holder.roomtype.text = rooms.roomtype
        holder.views.text = rooms.desc
        holder.img.setOnClickListener() {
            val intent = Intent(context,RoomDetails::class.java)
            intent.putExtra("rooms",rooms)
            context.startActivity(intent)
        }



        Glide.with(context).load(rooms.photo).into(holder.img)
        val imagePath = ServiceBuilder.loadImagePath() + rooms.photo
        if (!rooms.photo.equals("no-photo.jpg")) {
            Glide.with(context)
                .load(imagePath)
                .fitCenter()
                .into(holder.img)
        }
    }

    override fun getItemCount(): Int {
        showNotification(lstRooms.size)
        return lstRooms.size
    }


    private fun showNotification(size: Int) {
        val notificationManager = NotificationManagerCompat.from(context!!)
        val activityIntent = Intent(context!!, DashboardActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, activityIntent, 0)

        val notificationChannels = notifications(context!!)
        notificationChannels.createNotificationChannels()

        val notification = NotificationCompat.Builder(context!!, notificationChannels.CHANNEL_1)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle("My notification")
            .setContentText("Rooms list are $size")
            .setColor(Color.BLUE)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(1, notification)
    }
}