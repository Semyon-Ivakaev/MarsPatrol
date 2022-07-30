package com.vertigo.marspatrol.data.remotesource.model

import com.google.gson.annotations.SerializedName

data class TemperatureApi(
    @SerializedName("soles")
    val soles: List<TempSol>
)

data class TempSol(
    @SerializedName("id")
    val id: String,
    @SerializedName("terrestrial_date")
    val terrestrial_date: String,
    @SerializedName("sol")
    val sol: String,
    @SerializedName("ls")
    val ls: String,
    @SerializedName("season")
    val season: String,
    @SerializedName("min_temp")
    val min_temp: String,
    @SerializedName("max_temp")
    val max_temp: String,
    @SerializedName("pressure")
    val pressure: String,
    @SerializedName("pressure_string")
    val pressure_string: String,
    @SerializedName("atmo_opacity")
    val atmo_opacity: String,
    @SerializedName("sunrise")
    val sunrise: String,
    @SerializedName("sunset")
    val sunset: String,
    @SerializedName("min_gts_temp")
    val min_gts_temp: String,
    @SerializedName("max_gts_temp")
    val max_gts_temp: String
)