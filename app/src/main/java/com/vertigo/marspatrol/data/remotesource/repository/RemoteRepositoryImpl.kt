package com.vertigo.marspatrol.data.remotesource.repository

import com.vertigo.marspatrol.data.remotesource.api.RetrofitInstance
import com.vertigo.marspatrol.data.remotesource.model.ApiResponse
import com.vertigo.marspatrol.domain.model.Camera
import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.domain.model.MarsRover
import com.vertigo.marspatrol.domain.model.MarsTemp
import com.vertigo.marspatrol.domain.repository.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteRepositoryImpl: RemoteRepository {
    private val api_key = "egW3m5WkZUxyaHfKxmfLdPIwHCEtjEpUoWhyzb1O"

    private val retrofitApi = RetrofitInstance.marsRoverRetrofitApiService

    private val tempRetrofitApi = RetrofitInstance.temperatureRetrofitApiService

    override suspend fun getNeededPhotoList(data: String, roverName: String): ApiResponse<List<MarsPhoto>> {
        val resultList = arrayListOf<MarsPhoto>()
        try {
            withContext(Dispatchers.Default) {
                retrofitApi.getNeededMarsRoverPhotos(roverName = roverName, earth_date = data, key = api_key).photos.map {
                    result -> resultList.add(
                    MarsPhoto(
                        id = result.id,
                        sol = result.sol,
                        earth_date = result.earth_date,
                        camera_name = result.camera.full_name,
                        rover_name = result.rover.name,
                        img_url = result.img_src
                    )
                    )
                }
            }
            return ApiResponse.Success(resultList)
        } catch (ex: java.lang.Exception) {
            return ApiResponse.Error(exception = ex)
        }
    }

    override suspend fun getDefaultSolForMarsRover(roverName: String): ApiResponse<MarsRover?> {
        var result: MarsRover? = null
        try {
            withContext(Dispatchers.Default) {
                val cameraList = arrayListOf<Camera>()
                val response = retrofitApi.getDefaultSolForMarsRover(roverName = roverName, key = api_key).rover
                response.cameras.map {
                    camera -> cameraList.add(
                        Camera(
                            name = camera.camera_name, full_name = camera.full_name
                        ))
                }

                result = MarsRover(
                    id = response.id, name = response.name, landing_date = response.landing_date, launch_date = response.launch_date,
                    status = response.status, max_sol = response.max_sol, max_date = response.max_date, total_photos = response.total_photos,
                    cameras = cameraList
                )
            }
            return ApiResponse.Success(data = result)
        } catch (ex: Exception) {
            return ApiResponse.Error(exception = ex)
        }
    }

    override suspend fun getTemperature(): ApiResponse<List<MarsTemp>> {
        val resultList = arrayListOf<MarsTemp>()
        try {
            withContext(Dispatchers.Default) {
                val response = tempRetrofitApi.getTemperature().soles
                for (n in 0..8) {
                    val result = response[n]
                    resultList.add(
                        MarsTemp(
                            id = result.id,
                            earth_date = result.terrestrial_date,
                            sol = result.sol,
                            ls = result.ls,
                            season = result.season,
                            min_temp = result.min_temp,
                            max_temp = result.max_temp,
                            pressure = result.pressure,
                            pressure_string = result.pressure_string,
                            atmo_opacity = result.atmo_opacity,
                            sunrise = result.sunrise,
                            sunset = result.sunset,
                            min_gts_temp = result.min_gts_temp,
                            max_gts_temp = result.max_gts_temp
                        )
                    )
                }
            }
            return ApiResponse.Success(data = resultList)
        } catch (ex: java.lang.Exception) {
            return ApiResponse.Error(exception = ex)
        }
    }
}