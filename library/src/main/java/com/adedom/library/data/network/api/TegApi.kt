package com.adedom.library.data.network.api

import com.adedom.teg.models.request.SignInRequest
import com.adedom.teg.models.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TegApi {

    @POST("api/auth/sign-in")
    suspend fun callSignIn(@Body signIn: SignInRequest): SignInResponse

}
