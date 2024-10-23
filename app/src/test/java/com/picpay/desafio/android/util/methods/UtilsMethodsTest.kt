package com.picpay.desafio.android.util.methods

 import com.picpay.desafio.android.data.local.entities.User
import com.picpay.desafio.android.util.models.UserResponse
import org.junit.Assert
import org.junit.Test

class UtilsMethodsTest {

    @Test
    fun `return User list when pass UserResponse list`() {
        var userResponseList = listOf(
            UserResponse(
                img = "https://picsum.photos/id/237/200/300",
                name = "Katherine",
                username = "Johnson",
                id = 0
            ),
            UserResponse(
                img = "https://picsum.photos/id/237/200/300",
                name = "Ada",
                username = "Lovelace",
                id = 0
            )
        )

        var userList = listOf(
            User(
                img = "https://picsum.photos/id/237/200/300",
                name = "Katherine",
                username = "Johnson",
                id = 0
            ),
            User(
                img = "https://picsum.photos/id/237/200/300",
                name = "Ada",
                username = "Lovelace",
                id = 0
            )
        )
        Assert.assertEquals(
            UtilsMethods.transformUserResponseListIntoUserList(userResponseList),
            userList
        )
    }
}