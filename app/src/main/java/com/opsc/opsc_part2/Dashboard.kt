package com.opsc.opsc_part2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Dashboard : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        val imgsettings = findViewById<ImageView>(R.id.imgSettingsDash)
        val txtwelcome = findViewById<TextView>(R.id.txtWelcome)

        imgsettings.setOnClickListener {
            val int = Intent(this, Settings::class.java)
            startActivity(int)
        }

        txtwelcome.setText("Welcome back, " /* add user firstname from database */)

        val btnlights : Button = findViewById(R.id.btnLights)
        val btndoors : Button = findViewById(R.id.btnDoors)
        val btnsensors : Button = findViewById(R.id.btnSensors)

        btnlights.setOnClickListener {
            val int = Intent(this, Lights::class.java)
            startActivity(int)
        }
        btndoors.setOnClickListener {
            val int = Intent(this, Doors::class.java)
            startActivity(int)
        }
        btnsensors.setOnClickListener {
            val int = Intent(this, Sensors::class.java)
            startActivity(int)
        }


    }
}