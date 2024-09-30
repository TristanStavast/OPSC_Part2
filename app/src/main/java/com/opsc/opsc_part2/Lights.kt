package com.opsc.opsc_part2

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Lights : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lights)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val imghomelights = findViewById<ImageView>(R.id.imgHomeLights)
        val swtchoutsidelight = findViewById<Switch>(R.id.swtchOutsideLight)
        val insidelight = findViewById<Switch>(R.id.swtchInsideLight)

        imghomelights.setOnClickListener {
            val int = Intent(this, Dashboard::class.java)
            startActivity(int)
        }

    }
}