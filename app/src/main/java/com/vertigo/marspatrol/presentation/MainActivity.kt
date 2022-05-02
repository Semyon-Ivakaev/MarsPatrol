package com.vertigo.marspatrol.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vertigo.marspatrol.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_mars_rover)
    }
}