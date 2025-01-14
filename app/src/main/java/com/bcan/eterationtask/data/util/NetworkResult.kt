package com.bcan.eterationtask.data.util

sealed class NetworkResult<out T> {

    data class OnSuccess<out T>(val data: T?, val message: String?) : NetworkResult<T>()

    data class OnError(val message: String?) : NetworkResult<Nothing>()

    data object OnLoading : NetworkResult<Nothing>()
}