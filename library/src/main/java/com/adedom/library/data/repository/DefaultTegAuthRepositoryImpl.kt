package com.adedom.library.data.repository

import com.adedom.library.data.network.source.TegDataSource
import com.adedom.library.domain.Resource
import com.adedom.library.domain.repository.DefaultTegAuthRepository
import com.adedom.teg.models.request.*
import com.adedom.teg.models.response.*
import okhttp3.MultipartBody

class DefaultTegAuthRepositoryImpl(
    private val dataSource: TegDataSource
) : BaseRepository(), DefaultTegAuthRepository {

    override suspend fun callSignIn(signIn: SignInRequest): Resource<SignInResponse> {
        return safeApiCall { dataSource.callSignIn(signIn) }
    }

    override suspend fun callSignUp(signUp: SignUpRequest): Resource<SignInResponse> {
        return safeApiCall { dataSource.callSignUp(signUp) }
    }

    override suspend fun callRefreshToken(refreshToken: RefreshTokenRequest): Resource<SignInResponse> {
        return safeApiCall { dataSource.callRefreshToken(refreshToken) }
    }

    override suspend fun callFetchPlayerInfo(): Resource<PlayerInfoResponse> {
        return safeApiCall { dataSource.callFetchPlayerInfo() }
    }

    override suspend fun callChangeImageProfile(imageFile: MultipartBody.Part): Resource<BaseResponse> {
        return safeApiCall { dataSource.callChangeImageProfile(imageFile) }
    }

    override suspend fun callPlayerState(state: String): Resource<BaseResponse> {
        return safeApiCall { dataSource.callPlayerState(state) }
    }

    override suspend fun callChangePassword(changePassword: ChangePasswordRequest): Resource<BaseResponse> {
        return safeApiCall { dataSource.callChangePassword(changePassword) }
    }

    override suspend fun callChangeProfile(changeProfile: ChangeProfileRequest): Resource<BaseResponse> {
        return safeApiCall { dataSource.callChangeProfile(changeProfile) }
    }

    override suspend fun callFetchRankPlayers(
        search: String,
        limit: Int
    ): Resource<RankPlayersResponse> {
        return safeApiCall { dataSource.callFetchRankPlayers(search, limit) }
    }

    override suspend fun callLogActiveOn(): Resource<BaseResponse> {
        return safeApiCall { dataSource.callLogActiveOn() }
    }

    override suspend fun callLogActiveOff(): Resource<BaseResponse> {
        return safeApiCall { dataSource.callLogActiveOff() }
    }

    override suspend fun callFetchItemCollection(): Resource<BackpackResponse> {
        return safeApiCall { dataSource.callFetchItemCollection() }
    }

    override suspend fun callItemCollection(itemCollectionRequest: ItemCollectionRequest): Resource<BaseResponse> {
        return safeApiCall { dataSource.callItemCollection(itemCollectionRequest) }
    }

}
