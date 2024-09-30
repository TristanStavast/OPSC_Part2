package com.opsc.opsc_part2

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class Lights : BaseActivity() {

    private val client = OkHttpClient()
    private var light = ""

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

        imghomelights.setOnClickListener {
            val int = Intent(this, Dashboard::class.java)
            startActivity(int)
        }

        insidelight.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast(this).showCustomToast("Inside light switched on", this)
                light = "inside"
                //turnLightOn()
            } else {
                Toast(this).showCustomToast("Inside light switched off", this)
                light = "inside"
                //turnLightOff()
            }
        }

        swtchoutsidelight.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast(this).showCustomToast("Inside light switched on", this)
                light = "outside"
                //turnLightOn()
            } else {
                Toast(this).showCustomToast("Inside light switched off", this)
                light = "outside"
                //turnLightOff()
            }
        }

    }


    fun turnLightOn() {
        val url = "http://192.168.4.2/lighton" + light

        val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), "")
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    println(response.body!!.string())
                }
            }
        })
    }

    // Method to turn the light off
    fun turnLightOff() {
        val url = "http://192.168.4.2/lightoff" + light

        val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), "")
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    println(response.body!!.string())
                }
            }
        })
    }
}