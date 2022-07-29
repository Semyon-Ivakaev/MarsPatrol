package com.vertigo.marspatrol.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vertigo.marspatrol.databinding.ItemMarsRoverBinding
import com.vertigo.marspatrol.domain.model.MarsPhoto

class MarsRoverPhotoAdapter(private val fragmentType: String): ListAdapter<MarsPhoto, RecyclerView.ViewHolder>(DiffPhotoUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMarsRoverBinding.inflate(inflater, parent, false)
        return MarsRoverPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MarsRoverPhotoViewHolder).bind(getItem(position), navigationType = fragmentType)
    }
}