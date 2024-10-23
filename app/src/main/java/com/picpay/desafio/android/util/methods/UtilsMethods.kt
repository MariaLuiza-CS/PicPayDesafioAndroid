package com.picpay.desafio.android.util.methods

import com.picpay.desafio.android.data.local.entities.User
import com.picpay.desafio.android.util.models.UserResponse

object UtilsMethods {
    fun `transformUserResponseListIntoUserList`(data: List<UserResponse>?): List<User> {
        var userList: List<User> = listOf(User(null, null, null, null))
        data?.map {
            User(
                id = it.id,
                name = it.name,
                username = it.username,
                img = it.img
            )
        }?.let {
            userList = it
        }
        return userList
    }
}