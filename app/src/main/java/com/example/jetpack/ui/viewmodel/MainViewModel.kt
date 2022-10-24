package com.example.jetpack.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack.data.model.TvShowResponse
import com.example.jetpack.data.repository.ShowRepository
import com.example.jetpack.data.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ShowRepository): ViewModel() {


    val response : MutableState<ApiState> = mutableStateOf(ApiState.Empty)

    init {
        getShows()
    }

    private fun getShows(){
        viewModelScope.launch {
            repository.getShows()
                .onStart {
                    response.value = ApiState.Loading
                }.catch { e ->
                    response.value = ApiState.Error(e.message.toString())
                }.collect {
                    response.value = ApiState.Success(it)
                }
        }
    }

}