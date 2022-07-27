package com.vertigo.marspatrol.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.vertigo.marspatrol.R
import com.vertigo.marspatrol.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavMenu
        val navController = findNavController(R.id.main_container)
        bottomNavigationView.setupWithNavController(
            navController = navController
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}