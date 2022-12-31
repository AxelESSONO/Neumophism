package com.axel.neumophism

import android.app.Application

class Apps : Application() {
    override fun onCreate() {
        super.onCreate()
        ThemeSettings.getInstance(this)?.refreshTheme()
    }
}