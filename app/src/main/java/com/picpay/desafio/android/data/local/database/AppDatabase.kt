package com.picpay.desafio.android.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.local.dao.UsersDao
import com.picpay.desafio.android.data.local.entities.User
import com.picpay.desafio.android.util.constants.Constants.Companion.DATABASE_NAME

@Database(entities = [User::class], exportSchema = true, version = 1)
abstract class AppDatabase
    : RoomDatabase() {
    abstract fun userDao(): UsersDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
