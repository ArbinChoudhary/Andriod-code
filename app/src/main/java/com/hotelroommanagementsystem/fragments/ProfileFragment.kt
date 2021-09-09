package com.hotelroommanagementsystem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.hotelroommanagementsystem.R
import com.hotelroommanagementsystem.api.ServiceBuilder
import com.hotelroommanagementsystem.db.UserDB
import com.hotelroommanagementsystem.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProfileFragment : Fragment() {
    private lateinit var fnames : TextView
    private lateinit var lnames : TextView
    private lateinit var mobilenos : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view  = inflater.inflate(R.layout.fragment_profile, container, false)



        fnames = view.findViewById(R.id.fname)
        lnames = view.findViewById(R.id.lname)
        mobilenos = view.findViewById(R.id.mobile)



        lifecycleScope.launch(Dispatchers.IO) {
            val repository = UserRepository()
            val response = repository.getDetails()

            if (response.success == true) {
                val userDatas = response.data
                UserDB.getInstance(requireContext()).getUserDAO().deleteUser(userDatas!!)
                UserDB.getInstance(requireContext()).getUserDAO().registerUser(userDatas!!)
               val userDetail = UserDB.getInstance(requireContext()).getUserDAO().getUsers()
                ServiceBuilder.userid = userDetail._id
                withContext(Dispatchers.Main) {
                    fnames.text = "${userDetail.firstname}"
                    lnames.text = "${userDetail.lastname}"
                    mobilenos.text = "${userDetail.mobileno}"




                }
            }

        }






        return  view
    }
}

