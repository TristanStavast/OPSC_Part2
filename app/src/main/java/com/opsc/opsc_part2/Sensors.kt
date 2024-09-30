package com.opsc.opsc_part2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Sensors : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sensors)

    val imghomesensors = findViewById<ImageView>(R.id.imgHomeSensors)
        imghomesensors.setOnClickListener {
            val int = Intent(this, Dashboard::class.java)
            startActivity(int)
        }

    }
}