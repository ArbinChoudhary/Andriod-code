package com.hotelroommanagementsystem.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hotelroommanagementsystem.Model.Room
import com.hotelroommanagementsystem.R
import com.hotelroommanagementsystem.adapters.RoomAdapter
import com.hotelroommanagementsystem.db.UserDB
import com.hotelroommanagementsystem.repository.RoomRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RoomFragment : Fragment() {
    lateinit var room_recycle: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val room = inflater.inflate(R.layout.fragment_view_review, container, false)


        room_recycle = room.findViewById(R.id.room_recycle)
        CoroutineScope(Dispatchers.IO).launch {
            val repository = RoomRepository()
            val response = repository.getRooms()
            if(response.success==true){
                val lst = response.data
                UserDB.getInstance(requireContext()).getRoomDAO().deleteRoom()
                UserDB.getInstance(requireContext()).getRoomDAO().insertRooms(lst)
                withContext(Dispatchers.Main){
                    val adapter = RoomAdapter(context!!,lst as MutableList<Room>)
                    room_recycle.adapter = adapter
                    room_recycle.layoutManager = LinearLayoutManager(context!!)
                }
            }

        }

        return room
    }
}

