package com.adedom.library.data.repository

import com.adedom.library.data.network.source.TegAuthDataSource
import com.adedom.library.domain.Resource
import com.adedom.library.domain.repository.DefaultTegAuthRepository
import com.adedom.teg.request.auth.SignInRequest
import com.adedom.teg.response.SignInResponse

class DefaultTegAuthRepositoryImpl(
    private val dataSource: TegAuthDataSource
) : BaseRepository(), DefaultTegAuthRepository {

    override suspend fun callSignIn(
        signIn: SignInRequest
    ): Resource<SignInResponse> = safeApiCall { dataSource.callSignIn(signIn) }

}
