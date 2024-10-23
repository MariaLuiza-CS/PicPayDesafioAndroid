package com.picpay.desafio.android.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.data.local.entities.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListAdapter(private val users: List<User>?) :
    RecyclerView.Adapter<UserListAdapter.UsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding = ListItemUserBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)
    }

    override fun getItemCount(): Int = users?.size ?: 0

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        with(holder) {
            with(users?.get(position)) {
                binding.name.text = this?.name
                binding.username.text = this?.username
                binding.progressBar.visibility = View.VISIBLE
                Picasso.get()
                    .load(this?.img)
                    .error(R.drawable.ic_round_account_circle)
                    .into(binding.picture, object : Callback {
                        override fun onSuccess() {
                            binding.progressBar.visibility = View.GONE
                        }

                        override fun onError(e: Exception?) {
                            binding.progressBar.visibility = View.GONE
                        }
                    })
            }
        }
    }

    inner class UsersViewHolder(val binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)
}
