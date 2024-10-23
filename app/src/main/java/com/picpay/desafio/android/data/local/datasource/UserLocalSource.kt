package com.picpay.desafio.android.data.local.datasource

import com.picpay.desafio.android.data.local.dao.UsersDao
import com.picpay.desafio.android.data.local.entities.User

class UserLocalSource(private val usersDao: UsersDao) {
    suspend fun getAll(): List<User> {
        return usersDao.getAll()
    }

    suspend fun insertAll(usersItems: User) {
        return usersDao.insertAll(usersItems)
    }
}
