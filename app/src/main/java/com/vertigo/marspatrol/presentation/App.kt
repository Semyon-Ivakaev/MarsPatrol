package com.vertigo.marspatrol.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    companion object {
        const val NAME_CURIOSITY = "Curiosity"
        const val NAME_PERSEVERANCE = "Perseverance"
        const val MARS_ROVER_TYPE = "MARS_ROVER"
        const val FAVORITES_TYPE = "FAVORITES"
    }
}