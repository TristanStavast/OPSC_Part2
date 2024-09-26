package com.opsc.opsc_part2

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Settings : BaseActivity() {

    companion object {
        const val CHANNEL_ID = "local_notification_channel"
        const val NOTIFICATION_ID = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)

        createNotificationChannel()
        val themeSwitch: Switch = findViewById(R.id.swtchDarkMode)

        val preferencesHelper = PreferencesHelper(this)
        val currentTheme = preferencesHelper.getTheme()

        themeSwitch.isChecked = currentTheme == PreferencesHelper.DARK_MODE

        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                preferencesHelper.setTheme(PreferencesHelper.DARK_MODE)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                preferencesHelper.setTheme(PreferencesHelper.LIGHT_MODE)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            recreate()
        }

        val notificationSwtitch: Switch = findViewById(R.id.swtchNotifications)
        notificationSwtitch.isChecked = preferencesHelper.areNotificationsEnabled()

        notificationSwtitch.setOnCheckedChangeListener { _, isChecked ->
            preferencesHelper.setNotificationsEnabled(isChecked)

            if (isChecked) {
                showNotification(
                    "Notifications Enabled",
                    "You will receive notifications from now on."
                )
            } else {

            }
        }


        val imgHomeSense = findViewById<ImageView>(R.id.imgHomeSense)

        imgHomeSense.setOnClickListener {
            val int = Intent(this, Dashboard::class.java)
            startActivity(int)
        }

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Local Notifications"
            val descriptionText = "This channel is used for local notifications."
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }
    @SuppressLint("MissingPermission")
    private fun showNotification(title: String, content: String) {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this))
        {
            notify(NOTIFICATION_ID, builder.build())
        }
    }
}