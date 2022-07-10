package com.vertigo.marspatrol.domain.model

data class MarsRover(
    val id: Int,
    val name: String,
    val landing_date: String,
    val launch_date: String,
    val status: String,
    val max_sol: String,
    val max_date: String,
    val total_photos: Int,
    val cameras: List<Camera>
)

data class Camera(
    val name: String,
    val full_name: String
)