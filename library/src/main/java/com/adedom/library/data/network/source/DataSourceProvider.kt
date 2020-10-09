package com.adedom.library.data.network.source

import com.adedom.library.BuildConfig
import com.adedom.library.data.network.api.TegApi
import com.adedom.library.sharedpreference.service.SessionManagerService
import com.adedom.teg.util.TegConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class DataSourceProvider(private val sessionManagerService: SessionManagerService) {

    fun getDataSource(): TegApi {
        val retrofit = Retrofit.Builder().apply {
            client(getDefaultOkHttpClient())
            addConverterFactory(GsonConverterFactory.create())
            baseUrl(TegConstant.BASE_URL)
        }.build()

        return retrofit.create(TegApi::class.java)
    }

    fun getTegDataSource(): TegApi {
        val header = OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                requestBuilder.addHeader("Authorization", sessionManagerService.accessToken)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
        }.build()

        val retrofit = Retrofit.Builder().apply {
            client(header)
            client(getDefaultOkHttpClient())
            addConverterFactory(GsonConverterFactory.create())
            baseUrl(TegConstant.BASE_URL)
        }.build()

        return retrofit.create(TegApi::class.java)
    }

    private fun getDefaultOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }

            connectTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
        }.build()
    }

}
