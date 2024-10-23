package com.picpay.desafio.android.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.util.constants.Constants.Companion.USER_TABLE_NAME

@Entity(tableName = USER_TABLE_NAME)
data class User(
    @PrimaryKey var id: Int?,
    @ColumnInfo("img") var img: String?,
    @ColumnInfo("name") var name: String?,
    @ColumnInfo("username") var username: String?
)
