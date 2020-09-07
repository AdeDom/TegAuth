package com.adedom.library.data.network.source

import com.adedom.teg.request.auth.SignInRequest
import com.adedom.teg.response.SignInResponse

class TegAuthDataSource(private val provider: DataSourceProvider) {

    suspend fun callSignIn(
        signIn: SignInRequest
    ): SignInResponse = provider.getTegAuthDataSource().callSignIn(signIn)

}
