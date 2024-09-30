package com.opsc.opsc_part2

import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.opsc.opsc_part2.MainActivity.Companion.username

class Dashboard : BaseActivity() {

    private var cancellationSignal : CancellationSignal? = null
    private var isAuthenticated = false

    private val authenticationCallback : BiometricPrompt.AuthenticationCallback
        get() = @RequiresApi(Build.VERSION_CODES.P)
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                notifyUser("Authentication Error : $errString")
            }
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                notifyUser("Lockdown Successful")
                isAuthenticated = true
                redirectToLockdown()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val imgsettings = findViewById<ImageView>(R.id.imgSettingsDash)
        val txtwelcome = findViewById<TextView>(R.id.txtWelcome)
        val lockdown = findViewById<Button>(R.id.lockdownButton)
        val btnlights : Button = findViewById(R.id.btnLights)
        val btndoors : Button = findViewById(R.id.btnDoors)
        val btnsensors : Button = findViewById(R.id.btnSensors)

        lockdown.setOnClickListener {
            val biometricPrompt = BiometricPrompt.Builder(this)
                .setTitle("Lockdown")
                .setSubtitle("Do you wish to lockdown the household?")
                .setDescription("Place your finger on the fingerprint sensor")
                .setNegativeButton("Cancel", this.mainExecutor, DialogInterface.OnClickListener { dialog, which ->
                    notifyUser("Lockdown Cancelled")
                }).build()

            biometricPrompt.authenticate(getCancellationSignal(), mainExecutor, authenticationCallback)

        }

        imgsettings.setOnClickListener {
            val int = Intent(this, Settings::class.java)
            startActivity(int)
        }

        txtwelcome.setText("Welcome back, " + username)

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

    private fun getCancellationSignal() : CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            notifyUser("Lockdown cancelled")
        }
        return cancellationSignal as CancellationSignal
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkBiometricSupport(): Boolean {
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (!keyguardManager.isDeviceSecure) {
            notifyUser("Fingerprint Permission is not enabled in settings")
            return false
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
            notifyUser("Fingerprint is not enabled")
            return false
        }
        return packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)
    }

    private fun notifyUser(message: String) {
        Toast(this).showCustomToast(message, this)
    }

    private fun redirectToLockdown()
    {
        val int = Intent(this, Lockdown::class.java)
        startActivity(int)
    }

    override fun onBackPressed() {

    }

}