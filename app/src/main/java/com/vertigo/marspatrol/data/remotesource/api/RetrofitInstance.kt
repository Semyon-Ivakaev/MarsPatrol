package com.vertigo.marspatrol.data.remotesource.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

    private val client = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(logger)
        .build()

    private val marsRoverRetrofit = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val temperatureRetrofit = Retrofit.Builder()
        .baseUrl("http://cab.inta-csic.es/r")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val marsRoverRetrofitApiService: MarsRoverApiService = marsRoverRetrofit.create(MarsRoverApiService::class.java)

    val temperatureRetrofitApiService: TempApiService = temperatureRetrofit.create(TempApiService::class.java)
}