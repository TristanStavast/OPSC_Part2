package com.opsc.opsc_part2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

abstract class BaseActivity : AppCompatActivity ()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        val preferencesHelper = PreferencesHelper(this)
        val theme = preferencesHelper.getTheme()

        when (theme)
        {
            PreferencesHelper.DARK_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            PreferencesHelper.LIGHT_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        super.onCreate(savedInstanceState)
    }
}