package com.hotelroommanagementsystem.util

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity

fun Activity.saveSharedPref(_id:String, email:String, password:String){
    val sharedPref=getSharedPreferences("MyPref", AppCompatActivity.MODE_PRIVATE)
    val editor=sharedPref.edit()
    editor.putString("_id",_id)
    editor.putString("email",email)
    editor.putString("password",password)
    editor.apply()
}
fun Activity.getSharedPref() {
    val sharedPref = getSharedPreferences("MyPref", AppCompatActivity.MODE_PRIVATE)
    val emailPref = sharedPref.getString("email", "")
    val passwordPref = sharedPref.getString("password", "")
}