package com.vertigo.marspatrol.data.remotesource.model

import com.google.gson.annotations.SerializedName

data class MarsRoverApi(
    @SerializedName("rover")
    val rover: MarsRoverInfo
)

data class MarsRoverInfo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("landing_date")
    val landing_date: String,
    @SerializedName("launch_date")
    val launch_date: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("max_sol")
    val max_sol: String,
    @SerializedName("max_date")
    val max_date: String,
    @SerializedName("total_photos")
    val total_photos: Int,
    @SerializedName("cameras")
    val cameras: List<RoverCamera>
)

data class RoverCamera(
    @SerializedName("name")
    val camera_name: String,
    @SerializedName("full_name")
    val full_name: String
)
