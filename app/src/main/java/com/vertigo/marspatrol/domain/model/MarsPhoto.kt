package com.vertigo.marspatrol.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "mars_photo_table")
data class MarsPhoto (
    @PrimaryKey
    val id: Int,
    val sol: String,
    val earth_date: String,
    val camera_name: String,
    val rover_name: String,
    val img_url: String,
    var isFavorite: Boolean = false
    ): Parcelable