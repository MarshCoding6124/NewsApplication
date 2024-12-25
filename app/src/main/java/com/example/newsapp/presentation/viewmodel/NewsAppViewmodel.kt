package com.example.newsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.ApiResponse
import com.example.newsapp.data.repositry.repositry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NewsAppViewmodel: ViewModel(){
    val repo = repositry()
    private val _state = MutableStateFlow(AppState())
    val state = _state.asStateFlow()
    init{
        getHeadLines()
    }
    fun getHeadLines(country:String="US"){
        viewModelScope.launch {
            repo.getHeadLines(country).collectLatest {
               if (it.loading==true){
                   _state.value = AppState(loading = true)
               }else if (it.error.isNullOrBlank().not()){
                   _state.value = AppState(error = it.error.toString())
               }else{
                   _state.value = AppState(data = it.data as ApiResponse?)
               }
            }
        }
    }

    fun getEverything(q:String="us"){
        viewModelScope.launch {
            repo.getEverything(q).collectLatest {
                if (it.loading==true){
                    _state.value = AppState(loading = true)
                }else if (it.error.isNullOrBlank().not()){
                    _state.value = AppState(error = it.error.toString())
                }else{
                    _state.value = AppState(data = it.data as ApiResponse?)
                }
            }
        }
    }
}

data class AppState(
    val loading : Boolean = false,
    val error : String = "",
    val data : ApiResponse? = null
)
