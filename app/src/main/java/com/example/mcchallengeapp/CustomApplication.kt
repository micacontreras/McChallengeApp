package com.example.mcchallengeapp

import dagger.hilt.android.HiltAndroidApp
import android.app.Application

@HiltAndroidApp
class CustomApplication : Application() {

    companion object {
        private lateinit var instance: CustomApplication
        fun get() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        throw RuntimeException("Test Crash") // Force a crash

    }
}