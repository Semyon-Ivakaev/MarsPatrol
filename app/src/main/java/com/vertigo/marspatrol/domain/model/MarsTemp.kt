package com.vertigo.marspatrol.domain.model

data class MarsTemp (
    val id: String,
    val earth_date: String,
    val sol: String,
    val ls: String,
    val season: String,
    val min_temp: String,
    val max_temp: String,
    val pressure: String,
    val pressure_string: String,
    val atmo_opacity: String,
    val sunrise: String,
    val sunset: String,
    val min_gts_temp: String,
    val max_gts_temp: String
    )