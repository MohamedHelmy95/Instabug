package com.example.instabug

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repo: MainRepo = MainRepo()
    val response = repo.response.asStateFlow()


    fun fetchData() {
        viewModelScope.launch(Dispatchers.Main) {
            repo.fetchData()
        }
    }
}