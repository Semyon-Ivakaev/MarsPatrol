package com.vertigo.marspatrol.data.remotesource.api

import com.vertigo.marspatrol.data.remotesource.model.MarsApiPhoto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarsRoverApiService {
    @GET("mars-photos/api/v1/rovers/{rover_name}/photos?")
    suspend fun getDefaultMarsRoverPhotos(
        @Path("rover_name")
        roverName: String,
        @Query("sol")
        sol: String,
        @Query("api_key")
        key: String): List<MarsApiPhoto>

    @GET("mars-photos/api/v1/rovers/{rover_name}/photos?")
    suspend fun getNeededMarsRoverPhotos(
        @Path("rover_name")
        roverName: String,
        @Query("earth_date")
        earth_date: String,
        @Query("api_key")
        key: String): List<MarsApiPhoto>
}