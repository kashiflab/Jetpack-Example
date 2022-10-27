package com.example.jetpack.data.repository

import android.content.Context
import com.example.jetpack.data.api.APIService
import com.example.jetpack.data.model.TvShowResponse
import com.example.jetpack.data.utils.NetworkUtils
import com.example.jetpack.db.ShowDB
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ShowRepository @Inject constructor(private val apiService: APIService,
                                         private val showDB: ShowDB,
                                         @ApplicationContext private val context: Context
                                         ) {

    suspend fun getShows() : Flow<TvShowResponse> = flow {

        if(NetworkUtils.isNetworkConnected(context)){
            val result = apiService.getAllShows()
            result.body()!!.forEach{
                showDB.getShowDao().addShows(it)
            }
            result.body()?.let { emit(it) }
        }else{
            val showFromDB = showDB.getShowDao().getShows()
            val showResponse = TvShowResponse()
            showResponse.addAll(showFromDB)
            emit(showResponse)
        }

    }.flowOn(Dispatchers.IO)
}