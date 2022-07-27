package com.vertigo.marspatrol.presentation.fragments.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vertigo.marspatrol.databinding.FragmentFavoritesBinding
import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.presentation.adapters.MarsRoverPhotoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentFavorites: Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    private lateinit var marsRoverPhotoAdapter: MarsRoverPhotoAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        createRecycler()
        favoritesViewModel.favoritePhotosViewModel.observe(viewLifecycleOwner, {
            result -> updateRecycler(result)
        })

        return binding.root
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


    private fun updateRecycler(updatePhotoList: List<MarsPhoto>?) {
        marsRoverPhotoAdapter = MarsRoverPhotoAdapter()
        marsRoverPhotoAdapter.submitList(updatePhotoList)
        with(binding) {
            photoRecycler.adapter = marsRoverPhotoAdapter
            photoRecycler.layoutManager = layoutManager
            photoRecycler.itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}