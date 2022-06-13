package com.vertigo.marspatrol.data.remotesource.api

import com.vertigo.marspatrol.data.remotesource.model.MarsPhoto
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsRoverApiService {
    @GET("mars-photos/api/v1/rovers/curiosity/photos?")
    suspend fun getCuriosityMarsRoverPhotos(
        @Query("earth_date")
        data: String,
        @Query("api_key")
        key: String): List<MarsPhoto>
}