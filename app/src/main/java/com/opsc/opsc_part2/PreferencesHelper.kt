package com.opsc.opsc_part2

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context)
{
    private val sharedPreferences : SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    companion object
    {
        const val THEME_KEY = "current_theme"
        const val LIGHT_MODE = "light"
        const val DARK_MODE = "dark"
        const val NOTIFICATION_KEY = "notifications_enabled"
    }

    fun setTheme(theme : String)
    {
        sharedPreferences.edit().putString(THEME_KEY, theme).apply()
    }

    fun getTheme() : String?
    {
        return sharedPreferences.getString(THEME_KEY, LIGHT_MODE)
    }

    fun setNotificationsEnabled(enabled : Boolean)
    {
        sharedPreferences.edit().putBoolean(NOTIFICATION_KEY, enabled).apply()
    }
    fun areNotificationsEnabled() : Boolean
    {
        return sharedPreferences.getBoolean(NOTIFICATION_KEY, true)
    }
}