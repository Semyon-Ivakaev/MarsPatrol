package com.vertigo.marspatrol.data.remotesource.model

import com.google.gson.annotations.SerializedName

data class MarsApiPhoto(
    @SerializedName("sol")
    val sol: String,
    @SerializedName("earth_date")
    val earth_date: String,
    @SerializedName("full_name")
    val full_name: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("img_src")
    val img_src: String,
)