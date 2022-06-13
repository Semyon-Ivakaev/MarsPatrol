package com.vertigo.marspatrol.data.remotesource.model

import com.google.gson.annotations.SerializedName

data class MarsPhoto(
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("date")
    val date: String,
    @SerializedName("explanation")
    val explanation: String,
    @SerializedName("media_type")
    val media_type: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("hdurl")
    val hdUrl: String?,
    @SerializedName("url")
    val url: String
)