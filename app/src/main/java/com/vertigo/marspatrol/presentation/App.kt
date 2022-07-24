package com.vertigo.marspatrol.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    companion object {
        const val NAME_CURIOSITY = "Curiosity"
        const val NAME_PERSEVERANCE = "Perseverance"
    }
}