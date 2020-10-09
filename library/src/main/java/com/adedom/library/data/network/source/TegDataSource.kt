package com.adedom.library.data.network.source

import com.adedom.teg.models.request.SignInRequest
import com.adedom.teg.models.response.SignInResponse

class TegDataSource(private val provider: DataSourceProvider) {

    suspend fun callSignIn(
        signIn: SignInRequest
    ): SignInResponse = provider.getDataSource().callSignIn(signIn)

}
