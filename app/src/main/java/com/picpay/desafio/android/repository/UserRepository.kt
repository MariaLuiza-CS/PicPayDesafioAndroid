package com.picpay.desafio.android.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.data.local.datasource.UserLocalSource
import com.picpay.desafio.android.data.local.entities.User
import com.picpay.desafio.android.data.network.PicPayService
import com.picpay.desafio.android.util.methods.UtilsMethods
import com.picpay.desafio.android.util.models.UserResponse
import com.picpay.desafio.android.util.models.UserServiceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryImp(
    private val apiService: PicPayService,
    private val userLocalSource: UserLocalSource
) : UserRepository {

    private val _liveData: MutableLiveData<UserServiceResponse> by lazy {
        MutableLiveData<UserServiceResponse>()
    }

    override suspend fun getUsers(): List<User> {
        return userLocalSource.getAll()
    }

    override fun getItemsFromApi(): LiveData<UserServiceResponse> {
        apiService.getUsers().enqueue(object : Callback<List<UserResponse>> {

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                _liveData.value =
                    UserServiceResponse(isSuccess = false, message = t.localizedMessage)
            }

            override fun onResponse(
                call: Call<List<UserResponse>>,
                response: Response<List<UserResponse>>
            ) {

                _liveData.value = UserServiceResponse(
                    isSuccess = response.code() == 200,
                    message = response.message(), data = response.body(),
                    code = response.code()
                )
            }
        })
        return _liveData
    }

    override suspend fun insertItemsIntoDatabase(data: List<UserResponse>?) {
        UtilsMethods.transformUserResponseListIntoUserList(data).forEach { user ->
            userLocalSource.insertAll(
                user
            )
        }
    }
}

interface UserRepository {
    suspend fun getUsers(): List<User>

    fun getItemsFromApi(): LiveData<UserServiceResponse>

    suspend fun insertItemsIntoDatabase(data: List<UserResponse>?)

}
