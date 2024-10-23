package com.picpay.desafio.android.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.data.network.PicPayService
import com.picpay.desafio.android.data.local.database.AppDatabase
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.data.local.datasource.UserLocalSource
import com.picpay.desafio.android.data.local.entities.User
import com.picpay.desafio.android.repository.UserRepositoryImp
import com.picpay.desafio.android.util.methods.UtilsMethods

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val repository: UserRepositoryImp by lazy {
        val appDatabase = AppDatabase.getDatabase(this)
        UserRepositoryImp(
            PicPayService.RetrofitFactory.service,
            UserLocalSource(appDatabase.userDao())
        )
    }

    private val viewModel: MainActivityViewModel by lazy {
        val factory = MainActivityViewModel.Factory(userRepository = repository)
        ViewModelProviders.of(this, factory)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.userListProgressBar.visibility = View.VISIBLE
    }

    private fun initObservers() {

        viewModel.apiResponseReturn.observe(this) {
            if (it.isSuccess) {
                binding.userListProgressBar.visibility = View.GONE
                binding.recyclerView.adapter = UserListAdapter(
                    UtilsMethods.transformUserResponseListIntoUserList(it.data)
                )
                viewModel.insertItemsIntoDatabase(it.data)
            } else {
                binding.userListProgressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
