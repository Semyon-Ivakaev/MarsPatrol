package com.vertigo.marspatrol.presentation.fragments.marstemp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vertigo.marspatrol.databinding.ActivityMainBinding
import com.vertigo.marspatrol.databinding.FragmentTempBinding

class FragmentMarsTemp: Fragment() {

    private var _binding: FragmentTempBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.v("App", "Temp")
        _binding = FragmentTempBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}