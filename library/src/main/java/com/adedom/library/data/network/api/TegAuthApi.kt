package com.adedom.library.data.network.api

import com.adedom.teg.request.auth.SignInRequest
import com.adedom.teg.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TegAuthApi {

    @POST("api/auth/sign-in")
    suspend fun callSignIn(@Body signIn: SignInRequest): SignInResponse

}
