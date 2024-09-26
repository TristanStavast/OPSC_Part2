package com.opsc.opsc_part2

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)

       val imgHomeSense = findViewById<ImageView>(R.id.imgHomeSense)

        imgHomeSense.setOnClickListener {
            val int = Intent(this, Dashboard::class.java)
            startActivity(int)
        }

    }
}