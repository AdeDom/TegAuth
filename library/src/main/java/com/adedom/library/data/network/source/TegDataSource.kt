package com.adedom.library.data.network.source

import com.adedom.teg.models.request.*
import com.adedom.teg.models.response.*
import okhttp3.MultipartBody

class TegDataSource(private val provider: DataSourceProvider) {

    suspend fun callSignIn(signIn: SignInRequest): SignInResponse {
        return provider.getDataSource().callSignIn(signIn)
    }

    suspend fun callSignUp(signUp: SignUpRequest): SignInResponse {
        return provider.getDataSource().callSignUp(signUp)
    }

    suspend fun callRefreshToken(refreshToken: RefreshTokenRequest): SignInResponse {
        return provider.getDataSource().callRefreshToken(refreshToken)
    }

    suspend fun callFetchPlayerInfo(): PlayerInfoResponse {
        return provider.getTegDataSource().callFetchPlayerInfo()
    }

    suspend fun callChangeImageProfile(imageFile: MultipartBody.Part): BaseResponse {
        return provider.getTegDataSource().callChangeImageProfile(imageFile)
    }

    suspend fun callPlayerState(state: String): BaseResponse {
        return provider.getTegDataSource().callPlayerState(state)
    }

    suspend fun callChangePassword(changePassword: ChangePasswordRequest): BaseResponse {
        return provider.getTegDataSource().callChangePassword(changePassword)
    }

    suspend fun callChangeProfile(changeProfile: ChangeProfileRequest): BaseResponse {
        return provider.getTegDataSource().callChangeProfile(changeProfile)
    }

    suspend fun callFetchRankPlayers(search: String, limit: Int): RankPlayersResponse {
        return provider.getTegDataSource().callFetchRankPlayers(search, limit)
    }

    suspend fun callLogActiveOn(): BaseResponse {
        return provider.getTegDataSource().callLogActiveOn()
    }

    suspend fun callLogActiveOff(): BaseResponse {
        return provider.getTegDataSource().callLogActiveOff()
    }

    suspend fun callFetchItemCollection(): BackpackResponse {
        return provider.getTegDataSource().callFetchItemCollection()
    }

    suspend fun callItemCollection(itemCollectionRequest: ItemCollectionRequest): BaseResponse {
        return provider.getTegDataSource().callItemCollection(itemCollectionRequest)
    }

}
