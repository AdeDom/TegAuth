package com.adedom.library.data.network

import com.adedom.library.sharedpreference.service.SessionManagerService
import okhttp3.Interceptor

class NetworkServiceFactory(
    sessionManagerService: SessionManagerService
) : ServiceFactory(sessionManagerService) {

    override fun interceptors(managerService: SessionManagerService): List<Interceptor> {
        val token = managerService.accessToken
        if (token.isNotBlank()) {
            val networkRequestHeader = NetworkRequestHeader("Authorization", token)
            return arrayListOf(NetworkRequestInterceptor(listOf(networkRequestHeader)))
        }
        return arrayListOf(NetworkRequestInterceptor(listOf()))
    }

}
