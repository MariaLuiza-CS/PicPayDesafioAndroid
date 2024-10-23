package com.picpay.desafio.android.util.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class UserServiceResponse(
    val data: List<UserResponse>? = null,
    val code: Int = 0,
    val message: String,
    val isSuccess: Boolean = true
)

@Parcelize
data class UserResponse(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String
) : Parcelable
