package com.picpay.desafio.android.presentation

import androidx.lifecycle.*
import com.picpay.desafio.android.data.local.entities.User
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.util.models.UserResponse
import com.picpay.desafio.android.util.models.UserServiceResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(private val userRepository: UserRepository) : ViewModel() {

    val apiResponseReturn: LiveData<UserServiceResponse> = userRepository.getItemsFromApi()

    fun insertItemsIntoDatabase(data: List<UserResponse>?) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                userRepository.insertItemsIntoDatabase(data)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                MainActivityViewModel(userRepository) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}

