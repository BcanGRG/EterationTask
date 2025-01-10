package com.bcan.eterationtask.data.util

import android.database.sqlite.SQLiteException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

fun <T> sendRequestWithResponse(serviceCall: suspend () -> Response<T>) =
    flow {
        emit(NetworkResult.OnLoading)
        emit(safeApiCallWithResponse { serviceCall() })
    }.flowOn(Dispatchers.IO)

private suspend fun <T> safeApiCallWithResponse(
    apiCall: suspend () -> Response<T>
): NetworkResult<T> {
    return try {
        val result = apiCall()
        when (result.isSuccessful) {
            true -> NetworkResult.OnSuccess(result.body(), result.message())
            false -> NetworkResult.OnError(result.message())
        }
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> NetworkResult.OnError(throwable.message)
            is HttpException -> NetworkResult.OnError(throwable.message)
            else -> NetworkResult.OnError(throwable.message)
        }
    }
}

suspend fun <T> safeCall(
    block: suspend () -> T
): T {
    return try {
        block()
    } catch (e: IOException) {
        throw IOException("Veri yüklenirken bir hata oluştu: ${e.message}")
    } catch (e: SQLiteException) {
        throw SQLiteException("Veritabanı işlemi sırasında bir hata oluştu: ${e.message}")
    } catch (e: Exception) {
        throw Exception("Beklenmeyen bir hata oluştu: ${e.message}")
    }
}