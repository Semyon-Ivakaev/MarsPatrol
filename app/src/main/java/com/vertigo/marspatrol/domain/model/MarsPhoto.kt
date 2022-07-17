package com.vertigo.marspatrol.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarsPhoto (
    val sol: String,
    val earth_date: String,
    val camera_name: String,
    val rover_name: String,
    val img_url: String
    ): Parcelable