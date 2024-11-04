package com.opsc.opsc_part2

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.net.HttpURLConnection
import java.net.URL
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


val client = OkHttpClient()

class Lights : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lights)

        //Setting window flags
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val imghomelights = findViewById<ImageView>(R.id.imgHomeLights)
        val swtchoutsidelight = findViewById<Switch>(R.id.swtchOutsideLight)
        val insidelight = findViewById<Switch>(R.id.swtchInsideLight)

        insidelight.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                toggleDevice("fan", "on")
            } else {
                toggleDevice("fan", "off")
            }
        }

        imghomelights.setOnClickListener {
            val int = Intent(this, Dashboard::class.java)
            startActivity(int)
        }

    }

    fun toggleDevice(device: String, action: String) {
        val url = "http://localhost:5000/api/Device/$device/$action"  // Adjust URL as needed

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                if (response.isSuccessful) {
                    println("Success: ${response.body?.string()}")
                } else {
                    println("Error: ${response.code}")
                }
            }
        })
    }

}