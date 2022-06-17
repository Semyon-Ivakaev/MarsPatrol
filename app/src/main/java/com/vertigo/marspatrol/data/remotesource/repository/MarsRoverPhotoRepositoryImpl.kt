package com.vertigo.marspatrol.data.remotesource.repository

import com.vertigo.marspatrol.data.remotesource.api.RetrofitInstance
import com.vertigo.marspatrol.domain.model.MarsPhoto
import com.vertigo.marspatrol.domain.repository.MarsRoverPhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarsRoverPhotoRepositoryImpl: MarsRoverPhotoRepository {
    private val api_key = "egW3m5WkZUxyaHfKxmfLdPIwHCEtjEpUoWhyzb1O"

    private val retrofitApi = RetrofitInstance.marsRoverRetrofitApiService

    override suspend fun getDefaultPhotoList(sol: String, roverName: String): List<MarsPhoto> {
        val resultList = arrayListOf<MarsPhoto>()
        try {
            withContext(Dispatchers.Default) {
                retrofitApi.getDefaultMarsRoverPhotos(sol = sol, roverName = roverName, key = api_key).map {
                    result -> resultList.add(
                    MarsPhoto(
                        sol = result.sol,
                        earth_date = result.earth_date,
                        camera_name = result.full_name,
                        rover_name = result.name,
                        img_url = result.img_src
                    )
                )
                }
            }
        } catch (ex: Exception) {}
        return resultList
    }

    override suspend fun getNeedPhotoList(data: String, roverName: String): List<MarsPhoto> {
        val resultList = arrayListOf<MarsPhoto>()
        try {
            withContext(Dispatchers.Default) {
                retrofitApi.getNeededMarsRoverPhotos(roverName = roverName, earth_date = data, key = api_key).map {
                    result -> resultList.add(
                    MarsPhoto(
                        sol = result.sol,
                        earth_date = result.earth_date,
                        camera_name = result.full_name,
                        rover_name = result.name,
                        img_url = result.img_src
                    )
                    )
                }
            }
        } catch (ex: java.lang.Exception) {}
        return resultList
    }
}