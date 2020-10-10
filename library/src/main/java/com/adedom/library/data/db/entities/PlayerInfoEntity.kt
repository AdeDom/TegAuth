package com.adedom.library.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "player_info")
data class PlayerInfoEntity(
    @SerializedName("player_id") @ColumnInfo(name = "player_id") val playerId: String = "",
    @SerializedName("username") @ColumnInfo(name = "username") val username: String? = null,
    @SerializedName("name") @ColumnInfo(name = "name") val name: String? = null,
    @SerializedName("image") @ColumnInfo(name = "image") val image: String? = null,
    @SerializedName("level") @ColumnInfo(name = "level") val level: Int? = null,
    @SerializedName("state") @ColumnInfo(name = "state") val state: String? = null,
    @SerializedName("gender") @ColumnInfo(name = "gender") val gender: String? = null,
    @SerializedName("birth_date") @ColumnInfo(name = "birth_date") val birthDate: String? = null,
)
