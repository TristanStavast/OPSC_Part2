package com.opsc.opsc_part2

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Sensors : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sensors)

        //Setting window flags
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val imghomedash : ImageView = findViewById(R.id.imgHomeSensors)
        imghomedash.setOnClickListener {
            val int = Intent(this, Dashboard::class.java)
            startActivity(int)
        }

        val ring : Button = findViewById(R.id.ringbuzzer)

        ring.setOnClickListener {
            Toast(this).showCustomToast("Buzzer rang", this)
        }


    }
}