package com.opsc.opsc_part2

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Doors : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_doors)

        //Setting window flags
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val imgdoorshome = findViewById<ImageView>(R.id.imgHomeDoors)
        val btnopen = findViewById<Button>(R.id.btnOpenDoor)

        imgdoorshome.setOnClickListener {
            val int = Intent(this, Dashboard::class.java)
            startActivity(int)
        }

        btnopen.setOnClickListener {

        }

    }
}