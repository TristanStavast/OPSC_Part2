package com.opsc.opsc_part2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Dashboard : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

//        val btnsettings = findViewById<Button>(R.id.btnSettings)
//
//        btnsettings.setOnClickListener {
//            val int = Intent(this, Settings::class.java)
//            startActivity(int)
//        }

        val btnlights : Button = findViewById(R.id.lightsButton)
        val btndoors : Button = findViewById(R.id.doorsButton)
        val btnsensors : Button = findViewById(R.id.sensorsButton)

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