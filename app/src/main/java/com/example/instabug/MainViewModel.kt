package com.example.instabug

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instabug.core.BaseResponse
import com.example.instabug.core.Word
import com.example.instabug.repo.MainRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repo: MainRepo = MainRepo()

    private val _response = MutableStateFlow<BaseResponse<List<Word>>?>(null)
    val response = _response.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch(Dispatchers.Main) {
            _response.emitAll(repo.fetchData())
        }
    }
}