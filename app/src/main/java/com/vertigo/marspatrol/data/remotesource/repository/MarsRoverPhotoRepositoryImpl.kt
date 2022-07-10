package com.vertigo.marspatrol.data.remotesource.repository

import android.util.Log
import com.vertigo.marspatrol.data.remotesource.api.RetrofitInstance
import com.vertigo.marspatrol.data.remotesource.model.ApiResponse
import com.vertigo.marspatrol.domain.model.Camera
import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.domain.model.MarsRover
import com.vertigo.marspatrol.domain.repository.MarsRoverPhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarsRoverPhotoRepositoryImpl: MarsRoverPhotoRepository {
    private val api_key = "egW3m5WkZUxyaHfKxmfLdPIwHCEtjEpUoWhyzb1O"

    private val retrofitApi = RetrofitInstance.marsRoverRetrofitApiService

    override suspend fun getDefaultPhotoList(sol: String, roverName: String): ApiResponse<List<MarsPhoto>> {
        val resultList = arrayListOf<MarsPhoto>()
        try {
            withContext(Dispatchers.Default) {
                retrofitApi.getDefaultMarsRoverPhotos(sol = sol, roverName = roverName, key = api_key).photos.map {
                    result -> resultList.add(
                    MarsPhoto(
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
        } catch (ex: Exception) {
            return ApiResponse.Error(exception = ex)
        }
    }

    override suspend fun getNeedPhotoList(data: String, roverName: String): ApiResponse<List<MarsPhoto>> {
        val resultList = arrayListOf<MarsPhoto>()
        try {
            withContext(Dispatchers.Default) {
                retrofitApi.getNeededMarsRoverPhotos(roverName = roverName, earth_date = data, key = api_key).photos.map {
                    result -> resultList.add(
                    MarsPhoto(
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
                Log.v("App", response.toString())
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
}