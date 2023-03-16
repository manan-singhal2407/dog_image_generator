package com.example.simpleviralgames.domain

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SimpleViralGames : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}