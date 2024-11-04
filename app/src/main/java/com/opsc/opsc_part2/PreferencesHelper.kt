package com.opsc.opsc_part2

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context)
{
    //Shared preferences to set theme
    private val sharedPreferences : SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    //Companion object for light and dark mode
    companion object
    {
        const val THEME_KEY = "current_theme"
        const val LIGHT_MODE = "light"
        const val DARK_MODE = "dark"
        const val NOTIFICATION_KEY = "notifications_enabled"
        const val LANGUAGE_KEY = "selected_language"
    }

    //Theme preferences
    fun setTheme(theme : String)
    {
        sharedPreferences.edit().putString(THEME_KEY, theme).apply()
    }

    fun getTheme() : String?
    {
        return sharedPreferences.getString(THEME_KEY, LIGHT_MODE)
    }

    //Notification preferences
    fun setNotificationsEnabled(enabled : Boolean)
    {
        sharedPreferences.edit().putBoolean(NOTIFICATION_KEY, enabled).apply()
    }
    fun areNotificationsEnabled() : Boolean
    {
        return sharedPreferences.getBoolean(NOTIFICATION_KEY, true)
    }

    //Language preferences
    fun setLanguage(languageCode: String) {
        sharedPreferences.edit().putString(LANGUAGE_KEY, languageCode).apply()
    }

    fun getLanguage(): String {
        return sharedPreferences.getString(LANGUAGE_KEY, "en") ?: "en"
    }
}