package com.opsc.opsc_part2

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Settings : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)

        val themeSwitch : Switch = findViewById(R.id.swtchDarkMode)

        val preferencesHelper = PreferencesHelper(this)
        val currentTheme = preferencesHelper.getTheme()

        themeSwitch.isChecked = currentTheme == PreferencesHelper.DARK_MODE

        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked)
            {
                preferencesHelper.setTheme(PreferencesHelper.DARK_MODE)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else
            {
                preferencesHelper.setTheme(PreferencesHelper.LIGHT_MODE)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            recreate()
        }



       val imgHomeSense = findViewById<ImageView>(R.id.imgHomeSense)

        imgHomeSense.setOnClickListener {
            val int = Intent(this, Dashboard::class.java)
            startActivity(int)
        }

    }
}