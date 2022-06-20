package com.vertigo.marspatrol.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vertigo.marspatrol.R
import com.vertigo.marspatrol.presentation.fragments.marsroverphoto.FragmentMarsRoverPhoto

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_container, FragmentMarsRoverPhoto())
                .commit()
        }
    }
}