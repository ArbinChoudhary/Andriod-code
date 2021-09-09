package com.hotelroommanagementsystem

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.hotelroommanagementsystem.api.ServiceBuilder
import com.hotelroommanagementsystem.db.UserDB
import com.hotelroommanagementsystem.Model.User
import com.hotelroommanagementsystem.repository.UserRepository
import com.hotelroommanagementsystem.util.saveSharedPref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity()  {
    private val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
    private lateinit var emails : EditText
    private lateinit var passwords : EditText

    private  lateinit var signup : TextView
    private  lateinit var btnlogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signup= findViewById(R.id.signup)
        btnlogin = findViewById(R.id.btnlogin)
        emails = findViewById(R.id.email)
        passwords = findViewById(R.id.password)

        checkRunTimePermission()

        btnlogin.setOnClickListener {
            login()
        }

        signup.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterUser::class.java))
        }

    }

    private fun checkRunTimePermission() {
        if (!hasPermission()) {
            requestPermission()

        }
    }

    private fun hasPermission(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                hasPermission = false
                break
            }
        }
        return hasPermission
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this@LoginActivity, permissions, 1)
    }

    fun login() {
        val user = User(email = emails.text.toString(), password = passwords.text.toString())
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response = repository.checkUser(user)
                val data = response.data
                val listdata = data?.get(0)
                val email = listdata?.email
                if (response.success == true) {
                    val user = User(_id = response.id!!, email = emails.text.toString(), password = passwords.text.toString())
                    UserDB.getInstance(this@LoginActivity).getUserDAO().deleteUser()
                    val sharedPref = getSharedPreferences("MyPref", AppCompatActivity.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.remove("_id")
                    editor.remove("email")
                    editor.remove("password")
                        .apply()
                    UserDB.getInstance(this@LoginActivity).getUserDAO().registerUser(user)
                    saveSharedPref(_id = response.id!!, email = emails.text.toString(), password = passwords.text.toString())
                    ServiceBuilder.token = "Bearer ${response.token}"
                    ServiceBuilder.userid = response.id
                    withContext(Main) {
                        Toast.makeText(this@LoginActivity, "${response.id}", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                    }
                } else {
                    withContext(Main) {
                        Toast.makeText(this@LoginActivity, "error", Toast.LENGTH_SHORT)
                            .show()

                    }
                }
            }catch (ex:Exception){
                withContext(Main){
                    Toast.makeText(this@LoginActivity, "Invalid Credential", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }






}