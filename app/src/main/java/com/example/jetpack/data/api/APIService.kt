package com.example.jetpack.data.api

import com.example.jetpack.data.model.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("shows")
    suspend fun getAllShows() : Response<TvShowResponse>
}