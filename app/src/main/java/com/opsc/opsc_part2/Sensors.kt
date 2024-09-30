package com.opsc.opsc_part2

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class Sensors : BaseActivity() {

    private val client = OkHttpClient()

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
        val gas = findViewById<Switch>(R.id.swtchGasOnOff)
        val photocell = findViewById<Switch>(R.id.swtchPhotocell)
        val motion = findViewById<Switch>(R.id.swtchMotionSensor)
        val humidity = findViewById<Switch>(R.id.swtchHumidity)

        ring.setOnClickListener {
            Toast(this).showCustomToast("Buzzer rang", this)
            //ringBuzzer()
        }

        gas.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast(this).showCustomToast("Gas control switched on", this)
                //gasControl()
            } else {
                Toast(this).showCustomToast("Gas control switched off", this)
                //gasControl()
            }
        }

        photocell.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast(this).showCustomToast("Photo Cell control switched on", this)
                //photoCellControl()
            } else {
                Toast(this).showCustomToast("Photo Cell control switched off", this)
                //photoCellControl()
            }
        }

        motion.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast(this).showCustomToast("Motion control switched on", this)
                //motionControl()
            } else {
                Toast(this).showCustomToast("Motion control switched off", this)
                //motionControl()
            }
        }

        humidity.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast(this).showCustomToast("Humidity control switched on", this)
                //humidityControl()
            } else {
                Toast(this).showCustomToast("Humidity control switched off", this)
                //humidityControl()
            }
        }


    }


    // Method for ring buzzer
    fun ringBuzzer() {
        val url = "http://192.168.4.2/ringbuzzer"

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

    // Method for gas control
    fun gasControl() {
        val url = "http://192.168.4.2/gascontrol"

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

    // Method for photo cell control
    fun photoCellControl() {
        val url = "http://192.168.4.2/photocellcontrol"

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

    // Method for motion control
    fun motionControl() {
        val url = "http://192.168.4.2/motioncontrol"

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

    // Method for humidity control
    fun humidityControl() {
        val url = "http://192.168.4.2/humiditycontroll"

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