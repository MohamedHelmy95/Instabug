package com.example.instabug

sealed class BaseResponse<out T : Any> {
    data class Loading(val loading: Boolean) : BaseResponse<Nothing>()
    data class Success<out T : Any>(val data: T) : BaseResponse<T>()
    data class Error(val throwable: Throwable) : BaseResponse<Nothing>()
}