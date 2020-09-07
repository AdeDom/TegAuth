package com.adedom.library.data.repository

import com.adedom.teg.request.auth.SignInRequest
import com.adedom.teg.response.SignInResponse
import com.adedom.library.data.network.source.TegAuthDataSource
import com.adedom.library.domain.repository.DefaultTegAuthRepository

class DefaultTegAuthRepositoryImpl(
    private val dataSource: TegAuthDataSource
) : DefaultTegAuthRepository {

    override suspend fun callSignIn(
        signIn: SignInRequest
    ): SignInResponse = dataSource.callSignIn(signIn)

}
