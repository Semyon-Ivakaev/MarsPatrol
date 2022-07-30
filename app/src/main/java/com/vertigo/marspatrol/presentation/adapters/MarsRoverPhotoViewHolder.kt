package com.vertigo.marspatrol.presentation.adapters

import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vertigo.marspatrol.databinding.ItemMarsRoverBinding
import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.presentation.App
import com.vertigo.marspatrol.presentation.fragments.favorites.FragmentFavoritesDirections
import com.vertigo.marspatrol.presentation.fragments.marsroverphoto.FragmentMarsRoverPhotoDirections

class MarsRoverPhotoViewHolder(private val binding: ItemMarsRoverBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MarsPhoto, navigationType: String) {
        with(binding) {
            loadPhoto(item.img_url, itemMarsRoverPhoto)
        }
        itemView.setOnClickListener { view ->
            when (navigationType) {
                App.FAVORITES_TYPE -> {
                    val data = FragmentFavoritesDirections.actionFragmentFavoritesToFragmentDetails(item)
                    view.findNavController().navigate(data)
                }
                App.MARS_ROVER_TYPE -> {
                    val data = FragmentMarsRoverPhotoDirections.actionFragmentMarsRoverPhotoToFragmentDetails(item)
                    view.findNavController().navigate(data)
                }
            }

        }
    }

    private fun loadPhoto(url: String, imageView: ImageView) {
        Glide.with(binding.root)
            .load(url)
            .fitCenter()
            .into(imageView)
    }
}