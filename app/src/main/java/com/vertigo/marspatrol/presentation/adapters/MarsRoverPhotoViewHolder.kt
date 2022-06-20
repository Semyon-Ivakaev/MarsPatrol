package com.vertigo.marspatrol.presentation.adapters

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.vertigo.marspatrol.databinding.ItemMarsRoverBinding
import com.vertigo.marspatrol.domain.model.MarsPhoto

class MarsRoverPhotoViewHolder(private val binding: ItemMarsRoverBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MarsPhoto) {
        with(binding) {

        }
    }

    private fun loadPhoto(url: String, imageView: ImageView) {

    }
}