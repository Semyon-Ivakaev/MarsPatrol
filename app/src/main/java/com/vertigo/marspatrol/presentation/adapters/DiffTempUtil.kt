package com.vertigo.marspatrol.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.vertigo.marspatrol.domain.model.MarsTemp

class DiffTempUtil: DiffUtil.ItemCallback<MarsTemp>() {
    override fun areItemsTheSame(oldItem: MarsTemp, newItem: MarsTemp): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MarsTemp, newItem: MarsTemp): Boolean {
        return oldItem.id == newItem.id
    }
}