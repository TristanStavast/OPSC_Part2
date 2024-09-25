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
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //Private declarations
    private var cancellationSignal : CancellationSignal? = null
    private var isAuthenticated = false

    //Biometric authentication callback object
    private val authenticationCallback : BiometricPrompt.AuthenticationCallback
        get() = @RequiresApi(Build.VERSION_CODES.P)
        object  : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                notifyUser("Authentication Error : $errString")
            }

            //Called when authentication succeeds
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                notifyUser("Login Successful")
                isAuthenticated = true
                navigateToDashboard()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setting up the layout for fingerprint
        findViewById<Button>(R.id.btnFingerprint).setOnClickListener {
            val biometricPrompt = BiometricPrompt.Builder(this)
                .setTitle("Login with fingerprint")
                .setSubtitle("Do you wish to login with your fingerprint?")
                .setDescription("Place your finger on the fingerprint sensor")
                .setNegativeButton("Cancel", this.mainExecutor, DialogInterface.OnClickListener { dialog, which ->
                    notifyUser("Login Cancelled")
                }).build()

            biometricPrompt.authenticate(getCancellationSignal(), mainExecutor, authenticationCallback)
        }

        val btnlogin: Button = findViewById(R.id.btnLogin)
        val btnregister: Button = findViewById(R.id.btnRegisterLogin)
        var txtusername: EditText = findViewById(R.id.txtUserLogin)
        var txtpassword: EditText = findViewById(R.id.txtPassLogin)

        btnlogin.setOnClickListener {

            if (txtusername.text.toString().equals("") || txtpassword.text.toString().equals("")) {
                Toast(this).showCustomToast("Please enter all fields!", this)
            }
            else
            {
                Toast(this).showCustomToast("Logged in successfully", this)
                navigateToDashboard()
            }
        }

        btnregister.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    //Get cancellation signal if fingerprint is cancelled
    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            notifyUser("Login was cancelled by user")
        }
        return cancellationSignal as CancellationSignal
    }

    //Checking biometric support
    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkBiometricSupport(): Boolean {
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (!keyguardManager.isDeviceSecure) {
            notifyUser("Fingerprint Login Permission is not enabled in settings")
            return false
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
            notifyUser("Fingerprint login is not enabled")
            return false
        }
        return packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)
    }

    //Method to notify user using custom toast
    private fun notifyUser(message: String) {
       Toast(this).showCustomToast(message, this)
    }

    //Method to navigate to the dashboard
    private fun navigateToDashboard() {
        val intent = Intent(this, Dashboard::class.java)
        startActivity(intent)
    }
}


