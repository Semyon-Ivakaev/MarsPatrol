package com.vertigo.marspatrol.data.remotesource.model

import com.google.gson.annotations.SerializedName

data class MarsApiPhoto(
    @SerializedName("photos")
    val photos: List<Photos>
)

data class Photos(
    @SerializedName("id")
    val id: Int,
    @SerializedName("sol")
    val sol: String,
    @SerializedName("earth_date")
    val earth_date: String,
    val camera: Camera,
    val rover: Rover,
    @SerializedName("img_src")
    val img_src: String,
)

data class Camera(
    @SerializedName("full_name")
    val full_name: String
)

data class Rover(
    @SerializedName("name")
    val name: String,
)