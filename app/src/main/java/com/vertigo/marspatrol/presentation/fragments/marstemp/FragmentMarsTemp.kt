package com.vertigo.marspatrol.presentation.fragments.marstemp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vertigo.marspatrol.databinding.ActivityMainBinding
import com.vertigo.marspatrol.databinding.FragmentTempBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMarsTemp: Fragment() {

    private var _binding: FragmentTempBinding? = null
    private val binding get() = _binding!!

    private val marsTempViewModel: MarsTempViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.v("App", "Temp")
        marsTempViewModel.getMarsTempList()
        _binding = FragmentTempBinding.inflate(inflater, container, false)

        marsTempViewModel.marsTempList.observe(viewLifecycleOwner, {
            result ->
            Log.v("App", result.toString())
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}