package com.opsc.opsc_part2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnlogin: Button = findViewById(R.id.btnLogin)
        val btnregister : Button= findViewById(R.id.btnRegister)
        btnlogin.setOnClickListener {
            var int = Intent(this, Dashboard::class.java)
            startActivity(int)
        }
        btnregister.setOnClickListener {
            var int = Intent(this, Register::class.java)
            startActivity(int)
        }
    }
}