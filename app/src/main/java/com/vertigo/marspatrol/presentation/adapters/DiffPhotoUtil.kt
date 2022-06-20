package com.vertigo.marspatrol.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.vertigo.marspatrol.domain.model.MarsPhoto

class DiffPhotoUtil: DiffUtil.ItemCallback<MarsPhoto>() {
    override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
        return oldItem.sol == newItem.sol && oldItem.camera_name == newItem.camera_name &&
                oldItem.earth_date == newItem.earth_date && oldItem.img_url == newItem.img_url &&
                oldItem.rover_name == newItem.rover_name
    }
}