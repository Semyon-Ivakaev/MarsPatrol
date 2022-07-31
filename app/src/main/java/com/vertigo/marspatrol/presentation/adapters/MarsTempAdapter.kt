package com.vertigo.marspatrol.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vertigo.marspatrol.databinding.ItemMarsTempBinding
import com.vertigo.marspatrol.domain.model.MarsTemp

class MarsTempAdapter(private val setMarsTempCallBack: ((MarsTemp) -> Unit)): androidx.recyclerview.widget.ListAdapter<MarsTemp, RecyclerView.ViewHolder>(DiffTempUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMarsTempBinding.inflate(inflater, parent, false)
        return MarsTempViewHolder(binding, setMarsTempCallBack)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MarsTempViewHolder).bind(getItem(position))
    }
}