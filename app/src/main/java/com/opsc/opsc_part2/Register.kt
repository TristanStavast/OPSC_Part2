package com.opsc.opsc_part2

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.database.database

class Register : BaseActivity() {

    //Companion object for database
    companion object
    {
        val db = Firebase.database
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // Make the activity fullscreen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val btnregister : Button = findViewById(R.id.btnRegister)
        var txtuserregister : EditText = findViewById(R.id.txtUserRegister)
        var txtpassregister : EditText = findViewById(R.id.txtPassRegister)
        var txtconfirmpassword : EditText = findViewById(R.id.txtConfirmPassword)

        btnregister.setOnClickListener {

            if (txtuserregister.text.toString().equals("") || txtpassregister.text.toString().equals("") || txtconfirmpassword.text.toString().equals(""))
            {
                Toast(this).showCustomToast("Please enter all fields!", this)
            }
            else if (!txtpassregister.text.toString().equals(txtconfirmpassword.text.toString()))
            {
                Toast(this).showCustomToast("Please ensure that your passwords match!", this)
            }
            else
            {
                val ref = db.getReference("Users/" + (MainActivity.userList.size + 1) + "/Username")
                ref.setValue(txtuserregister.text.toString())
                val ref2 = db.getReference("Users/" + (MainActivity.userList.size + 1) + "/Password")
                ref2.setValue(txtpassregister.text.toString())
                val ref3 = db.getReference("Users/" + (MainActivity.userList.size + 1) + "/FirstName")
                ref3.setValue("")
                val ref4 = db.getReference("Users/" + (MainActivity.userList.size + 1) + "/LastName")
                ref4.setValue("")

                Toast(this).showCustomToast("Registered successfully", this)
                val int = Intent(this, Dashboard::class.java)
                startActivity(int)
            }

        }

    }

    override fun onBackPressed() {

    }
}