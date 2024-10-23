package com.picpay.desafio.android.data.network

import com.picpay.desafio.android.util.constants.Constants.Companion.BASE_URL
import com.picpay.desafio.android.util.constants.Constants.Companion.GET_USERS_REQUEST
import com.picpay.desafio.android.util.models.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface PicPayService {

    @GET(GET_USERS_REQUEST)
    fun getUsers(): Call<List<UserResponse>>

    class RetrofitFactory {
        companion object {
            private val okHttpClient = OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
                .build()

            private val retrofit: Retrofit by lazy {
                Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            val service: PicPayService by lazy {
                retrofit.create(PicPayService::class.java)
            }
        }
    }
}