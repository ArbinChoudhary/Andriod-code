package com.hotelroommanagementsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hotelroommanagementsystem.api.ServiceBuilder
import com.hotelroommanagementsystem.Model.User
import com.hotelroommanagementsystem.repository.UserRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class SplashActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPref = getSharedPreferences("MyPref", AppCompatActivity.MODE_PRIVATE)
        val emailPref = sharedPref.getString("email", null)
        val passwordPref = sharedPref.getString("password", "")
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            if (emailPref != null) {
                withContext(Main) {
                    Toast.makeText(this@SplashActivity, "$emailPref", Toast.LENGTH_SHORT).show()
                    val repository = UserRepository()
                    val user = User(email = emailPref, password = passwordPref)
                    val response = repository.checkUser(user)
                    if (response.success == true) {
                        ServiceBuilder.token = "Bearer ${response.token}"
                        ServiceBuilder.userid = response.id
                        delay(2000)
                        startActivity(Intent(this@SplashActivity, DashboardActivity::class.java)
                        )
                        finish()
                    }
                }
            } else {
                withContext(Main) {
                    startActivity(
                            Intent(
                                    this@SplashActivity,
                                    LoginActivity::class.java
                            )
                    )
                }
                finish()
            }
        }

    }
}