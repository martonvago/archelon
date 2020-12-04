package com.martonvago.archelon

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ArchelonApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialise timezone information
        AndroidThreeTen.init(this)
    }
}