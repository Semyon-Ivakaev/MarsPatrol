package com.vertigo.marspatrol.presentation.fragments.marsroverphoto

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vertigo.marspatrol.databinding.FragmentMarsRoverBinding
import com.vertigo.marspatrol.presentation.adapters.MarsRoverPhotoAdapter

class FragmentMarsRoverPhoto: Fragment() {
    private var _binding: FragmentMarsRoverBinding? = null
    private val binding get() = _binding!!

    private lateinit var marsRoverPhotoViewModel: MarsRoverPhotoViewModel
    private lateinit var marsRoverPhotoAdapter: MarsRoverPhotoAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarsRoverBinding.inflate(inflater, container, false)
        val view = binding.root

        createRecycler()

        marsRoverPhotoViewModel = ViewModelProvider(this, MarsRoverViewModelFactory())[MarsRoverPhotoViewModel::class.java]

        marsRoverPhotoViewModel.marsRoverPhotos.observe(viewLifecycleOwner, {
            result -> Log.v("App", result.toString())
            marsRoverPhotoAdapter.submitList(result)
        })

        return view
    }

    private fun createRecycler() {
        marsRoverPhotoAdapter = MarsRoverPhotoAdapter()
        layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
        with(binding) {
            photoRecycler.adapter = marsRoverPhotoAdapter
            photoRecycler.layoutManager = layoutManager
            photoRecycler.itemAnimator = DefaultItemAnimator()
        }
    }
}