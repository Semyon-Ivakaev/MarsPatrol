package com.vertigo.marspatrol.presentation.fragments.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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
        initPullToRefresh()

        favoritesViewModel.getFavoritePhotosList()

        favoritesViewModel.favoritePhotosViewModel.observe(viewLifecycleOwner, {
            result -> updateRecycler(result)
        })

        return binding.root
    }


    private fun initPullToRefresh() {
        with(binding) {
            swipeRefresh.setOnRefreshListener {
                clearRecycler()
                showProgressBar(show = true)
                favoritesViewModel.getFavoritePhotosList()
                swipeRefresh.isRefreshing = false
            }
        }
    }


    private fun clearRecycler() {
        with(binding) {
            photoRecycler.layoutManager = null
        }
    }

    private fun createRecycler() {
        marsRoverPhotoAdapter = MarsRoverPhotoAdapter("FAVORITES")
        layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
        with(binding) {
         photoRecycler.adapter = marsRoverPhotoAdapter
         photoRecycler.layoutManager = layoutManager
         photoRecycler.itemAnimator = DefaultItemAnimator()
        }
    }


    private fun updateRecycler(updatePhotoList: List<MarsPhoto>?) {
        marsRoverPhotoAdapter = MarsRoverPhotoAdapter("FAVORITES")
        marsRoverPhotoAdapter.submitList(updatePhotoList)
        with(binding) {
            photoRecycler.adapter = marsRoverPhotoAdapter
            photoRecycler.layoutManager = layoutManager
            photoRecycler.itemAnimator = DefaultItemAnimator()
        }
    }

    private fun showErrorMessage(show: Boolean, messageText: String?) {
        with(binding) {
            errorMessageTextview.text = messageText
            errorMessageCardview.isVisible = show
        }
    }

    private fun showProgressBar(show: Boolean) {
        Log.v("App", "showProgressBar $show")
        with(binding) {
            progressBar.isVisible = show
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}