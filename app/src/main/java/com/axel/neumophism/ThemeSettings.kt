package com.axel.neumophism

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate


class ThemeSettings constructor(private val context: Context) {

    private object Key {
        const val NIGHT_MODE = "nightMode"
    }

    var nightMode: Boolean

    init {
        val prefs: SharedPreferences =
            context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        nightMode = prefs.getBoolean(Key.NIGHT_MODE, getCurrentMode())
    }

    fun save(context: Context) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences("settings", Context.MODE_PRIVATE).edit()
        editor.putBoolean(Key.NIGHT_MODE, nightMode)
        editor.apply()
    }

    fun refreshTheme() {
        AppCompatDelegate.setDefaultNightMode(if (nightMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun getCurrentMode(): Boolean {
        when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> return true
            Configuration.UI_MODE_NIGHT_NO -> return false
        }
        return true
    }

    companion object {
        private var instance: ThemeSettings? = null
        fun getInstance(context: Context): ThemeSettings? {
            if (instance == null) instance = ThemeSettings(context)
            return instance
        }
    }
}