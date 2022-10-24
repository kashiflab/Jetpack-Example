package com.example.jetpack.data.utils

import com.example.jetpack.data.model.TvShowResponse

sealed class ApiState {
    class Success(val data: TvShowResponse) : ApiState()
    class Error(val msg: String) : ApiState()
    object Loading : ApiState()
    object Empty : ApiState()
}