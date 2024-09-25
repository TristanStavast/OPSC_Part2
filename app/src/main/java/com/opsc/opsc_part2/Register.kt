package com.opsc.opsc_part2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        val btnregister : Button = findViewById(R.id.btnRegister)
        var txtuserregister : EditText = findViewById(R.id.txtUserRegister)
        var txtpassregister : EditText = findViewById(R.id.txtPassRegister)
        var txtconfirmpassword : EditText = findViewById(R.id.txtConfirmPassword)

        btnregister.setOnClickListener {

            if (txtuserregister.text.toString().equals("") || txtpassregister.text.toString().equals("") || txtconfirmpassword.text.toString().equals(""))
            {
                Toast(this).showCustomToast("Please enter all fields!", this)
            }
            else
            {
                Toast(this).showCustomToast("Registered successfully", this)
                val int = Intent(this, Dashboard::class.java)
                startActivity(int)
            }

        }

    }
}