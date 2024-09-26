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
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class MainActivity : AppCompatActivity() {

    //Private declarations
    private var cancellationSignal : CancellationSignal? = null
    private var isAuthenticated = false
    private lateinit var ref1: DatabaseReference

    companion object {
        val dbHS = Firebase.database
        val userList = mutableListOf<Users>()
        var SignedIn : Int = -1
    }

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

        // Make the activity fullscreen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

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

        ref1 = FirebaseDatabase.getInstance().getReference("Users")
        readFromFirebase()

        btnlogin.setOnClickListener {
            var found = false
            for(i in 0 until userList.size)
            {
                //error handling
                if((txtusername.text.toString().equals(userList[i].username)) && (txtpassword.text.toString().equals(
                        userList[i].password)))
                {
                    //logging in the user
                    Toast(this).showCustomToast("Logged in successfully", this)
                    navigateToDashboard()

                    found = true
                    SignedIn = i
                    break
                }
            }

            //error handling
            if(found == false)
            {
                txtusername.error = "Please enter valid username!"
                txtpassword.error = "Please enter valid password!"
                Toast(this).showCustomToast("Please enter all fields!", this)
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

    private fun readFromFirebase() {
        ref1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (userSnapshot in snapshot.children) {
                    val username = userSnapshot.child("Username").getValue(String::class.java)
                    val password = userSnapshot.child("Password").getValue(String::class.java)
                    val firstname = userSnapshot.child("FirstName").getValue(String::class.java)
                    val lastname = userSnapshot.child("LastName").getValue(String::class.java)
                    if (username != null && password != null && firstname != null && lastname != null) {
                        val user = Users(username, password, firstname, lastname)
                        userList.add(user)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    override fun onBackPressed() {

    }
}


