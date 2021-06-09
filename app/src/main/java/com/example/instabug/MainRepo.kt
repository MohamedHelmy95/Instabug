package com.example.instabug

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


class MainRepo {

    private val defaultDispatcher = Dispatchers.IO
    val response = MutableStateFlow<BaseResponse<String>?>(null)
    private val tag = "MainRepo"
    suspend fun fetchData() = response.emitAll(buildApiCall(task = {
        Util.parseBufferedString(
            input = Util.buildBufferedReader().readText()
        ) ?: ""
    }))


    private suspend fun <T : Any> buildApiCall(
        task: suspend () -> T
    ) = flow<BaseResponse<T>> {
        emit(BaseResponse.Success(data = task()))
    }
        .flowOn(defaultDispatcher)
        .onStart {
            emit(BaseResponse.Loading(loading = true))
        }
        .onCompletion { emit(BaseResponse.Loading(loading = false)) }
        .catch { throwable ->
            Log.e(tag, "buildApi: throwable $throwable")
            BaseResponse.Error(throwable = throwable)

        }


}