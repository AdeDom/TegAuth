package com.adedom.library.data.repository

import com.adedom.library.data.network.source.TegDataSource
import com.adedom.library.domain.Resource
import com.adedom.library.domain.repository.DefaultTegAuthRepository
import com.adedom.teg.models.request.SignInRequest
import com.adedom.teg.models.response.SignInResponse

class DefaultTegAuthRepositoryImpl(
    private val dataSource: TegDataSource
) : BaseRepository(), DefaultTegAuthRepository {

    override suspend fun callSignIn(
        signIn: SignInRequest
    ): Resource<SignInResponse> = safeApiCall { dataSource.callSignIn(signIn) }

}
