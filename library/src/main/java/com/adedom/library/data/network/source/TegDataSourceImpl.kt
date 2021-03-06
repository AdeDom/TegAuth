package com.adedom.library.data.network.source

import androidx.lifecycle.LiveData
import com.adedom.library.data.db.AppDatabase
import com.adedom.library.data.db.entities.PlayerInfoEntity
import com.adedom.teg.models.request.*
import com.adedom.teg.models.response.*
import okhttp3.MultipartBody

class TegDataSourceImpl(
    private val db: AppDatabase,
    private val provider: DataSourceProvider,
) : TegDataSource {

    override suspend fun savePlayerInfo(playerInfo: PlayerInfoEntity) {
        return db.getPlayerInfoDao().savePlayerInfo(playerInfo)
    }

    override suspend fun getDbPlayerInfo(): PlayerInfoEntity? {
        return db.getPlayerInfoDao().getDbPlayerInfo()
    }

    override fun getDbPlayerInfoLiveData(): LiveData<PlayerInfoEntity> {
        return db.getPlayerInfoDao().getDbPlayerInfoLiveData()
    }

    override suspend fun deletePlayerInfo() {
        return db.getPlayerInfoDao().deletePlayerInfo()
    }

    override suspend fun callSignIn(signIn: SignInRequest): SignInResponse {
        return provider.getDataSource().callSignIn(signIn)
    }

    override suspend fun callSignUp(signUp: SignUpRequest): SignInResponse {
        return provider.getDataSource().callSignUp(signUp)
    }

    override suspend fun callRefreshToken(refreshToken: RefreshTokenRequest): SignInResponse {
        return provider.getDataSource().callRefreshToken(refreshToken)
    }

    override suspend fun callFetchPlayerInfo(): PlayerInfoResponse {
        return provider.getTegDataSource().callFetchPlayerInfo()
    }

    override suspend fun callChangeImageProfile(imageFile: MultipartBody.Part): BaseResponse {
        return provider.getTegDataSource().callChangeImageProfile(imageFile)
    }

    override suspend fun callPlayerState(state: String): BaseResponse {
        return provider.getTegDataSource().callPlayerState(state)
    }

    override suspend fun callChangePassword(changePassword: ChangePasswordRequest): BaseResponse {
        return provider.getTegDataSource().callChangePassword(changePassword)
    }

    override suspend fun callChangeProfile(changeProfile: ChangeProfileRequest): BaseResponse {
        return provider.getTegDataSource().callChangeProfile(changeProfile)
    }

    override suspend fun callFetchRankPlayers(search: String, limit: Int): RankPlayersResponse {
        return provider.getTegDataSource().callFetchRankPlayers(search, limit)
    }

    override suspend fun callLogActiveOn(): BaseResponse {
        return provider.getTegDataSource().callLogActiveOn()
    }

    override suspend fun callLogActiveOff(): BaseResponse {
        return provider.getTegDataSource().callLogActiveOff()
    }

    override suspend fun callFetchItemCollection(): BackpackResponse {
        return provider.getTegDataSource().callFetchItemCollection()
    }

    override suspend fun callItemCollection(itemCollectionRequest: ItemCollectionRequest): BaseResponse {
        return provider.getTegDataSource().callItemCollection(itemCollectionRequest)
    }

}
