package com.adedom.library.data.network

import com.adedom.teg.data.BASE_URL
import com.adedom.library.sharedpreference.service.SessionManagerService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class ServiceFactory(private val sessionManagerService: SessionManagerService) {

    fun <T> create(serviceType: Class<T>): T = retrofit().create(serviceType)

    private fun retrofit(): Retrofit {
        val builder = Retrofit.Builder()
            .client(getHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
        return builder.build()
    }

    private fun getHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        interceptors(sessionManagerService).forEach { interceptor ->
            builder.addInterceptor(interceptor)
        }
        builder.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
        return builder.build()
    }

    abstract fun interceptors(managerService: SessionManagerService): List<Interceptor>

}
