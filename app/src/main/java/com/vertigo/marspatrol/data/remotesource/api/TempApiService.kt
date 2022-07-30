package com.vertigo.marspatrol.data.remotesource.api

import com.vertigo.marspatrol.data.remotesource.model.TemperatureApi
import retrofit2.http.GET

interface TempApiService {
    @GET("rems/wp-content/plugins/marsweather-widget/api.php")
    suspend fun getTemperature(): TemperatureApi
}