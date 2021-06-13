package com.example.instabug.repo

import com.example.instabug.core.BaseResponse
import com.example.instabug.core.Word
import com.example.instabug.utility.Mapper.getStringListFromHtml
import com.example.instabug.utility.Mapper.getWordList
import com.example.instabug.utility.Network.buildBufferedReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


class MainRepo {

    private val defaultDispatcher = Dispatchers.IO

    suspend fun fetchData(): Flow<BaseResponse<List<Word>>> = buildApiCall(task = {
        val bufferReader = buildBufferedReader()

        val pageHtmlText = bufferReader.readText()

        val stringList = getStringListFromHtml(input = pageHtmlText)

        getWordList(stringList = stringList)
    })


    private suspend fun <T : Any> buildApiCall(task: suspend () -> T) = flow<BaseResponse<T>>
    { emit(BaseResponse.Success(data = task())) }
        .flowOn(defaultDispatcher)
        .onStart { emit(BaseResponse.Loading(loading = true)) }
        .onCompletion { emit(BaseResponse.Loading(loading = false)) }
        .catch { throwable -> BaseResponse.Error(throwable = throwable) }


}