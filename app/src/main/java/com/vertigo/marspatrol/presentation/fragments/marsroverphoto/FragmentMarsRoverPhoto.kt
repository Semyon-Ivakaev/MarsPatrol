package com.vertigo.marspatrol.presentation.fragments.marsroverphoto

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vertigo.marspatrol.databinding.FragmentMarsRoverBinding

class FragmentMarsRoverPhoto: Fragment() {
    private var _binding: FragmentMarsRoverBinding? = null
    private val binding get() = _binding!!

    private lateinit var marsRoverPhotoViewModel: MarsRoverPhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarsRoverBinding.inflate(inflater, container, false)
        val view = binding.root

        marsRoverPhotoViewModel = ViewModelProvider(this, MarsRoverViewModelFactory())[MarsRoverPhotoViewModel::class.java]

        marsRoverPhotoViewModel.marsRoverPhotos.observe(viewLifecycleOwner, {
            result -> Log.v("App", result.toString())
        })

        return view
    }
}