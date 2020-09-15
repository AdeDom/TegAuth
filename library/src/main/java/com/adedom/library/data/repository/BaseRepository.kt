package com.adedom.library.data.repository

import com.adedom.library.domain.Resource

abstract class BaseRepository {

    suspend fun <T : Any> safeApiCall(apiCall: suspend () -> T): Resource<T> =
        try {
            Resource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            Resource.Error(throwable)
        }

}
