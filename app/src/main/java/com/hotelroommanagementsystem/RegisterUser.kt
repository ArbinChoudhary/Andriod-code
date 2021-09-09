package com.hotelroommanagementsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hotelroommanagementsystem.api.ServiceBuilder
import com.hotelroommanagementsystem.Model.User
import com.hotelroommanagementsystem.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RegisterUser : AppCompatActivity() {
    private lateinit var etfirstname: EditText;
    private lateinit var etlastname: EditText;
    private lateinit var etemail: EditText;
    private lateinit var etpassword: EditText;
    private lateinit var etcpassword: EditText;
    private lateinit var etMobileno : EditText;
    private lateinit var btnregister: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)
        etfirstname = findViewById(R.id.etfirstname)
        etlastname = findViewById(R.id.etlastname)
        etemail = findViewById(R.id.etemail)
        etpassword = findViewById(R.id.etpassword)
        etcpassword = findViewById(R.id.etcpassword)
        etMobileno = findViewById(R.id.etmobile)

        btnregister = findViewById(R.id.btnregister)
        btnregister.setOnClickListener() {
            val firstname = etfirstname.text.toString()
            val lastname = etlastname.text.toString()
            val email = etemail.text.toString()
            val password = etpassword.text.toString()
            val cpassword = etcpassword.text.toString()
            val mobileno = etMobileno.text.toString()

            if (password != cpassword) {
                etpassword.error = "Password does not match"
                etpassword.requestFocus()
                return@setOnClickListener
            } else {
                val user = User(firstname = firstname, lastname = lastname, email = email, password = password, mobileno = mobileno)
                CoroutineScope(Dispatchers.IO).launch {

                    val repository = UserRepository()
                    val response = repository.registerUser(user)
                    if (response.success == true) {
                        ServiceBuilder.token = response.token
                        withContext(Main) {
                            Toast.makeText(this@RegisterUser, "Success", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@RegisterUser, LoginActivity::class.java))
                        }
                    } else {
                        withContext(Main) {
                            Toast.makeText(this@RegisterUser, "Error registering user", Toast.LENGTH_SHORT).show()
                        }

                    }
                }
            }
        }
    }
}

