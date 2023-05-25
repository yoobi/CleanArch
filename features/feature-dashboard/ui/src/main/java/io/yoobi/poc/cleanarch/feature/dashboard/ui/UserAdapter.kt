package io.yoobi.poc.cleanarch.feature.dashboard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.yoobi.poc.cleanarch.feature.dashboard.domain.model.User
import io.yoobi.poc.cleanarch.features.dashboard.ui.databinding.ItemUserBinding

class UserAdapter(private val clickListener: UserClickListener)
    : ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let { user ->
            holder.bind(user)
            holder.itemView.setOnClickListener { clickListener.onClick(user.name) }
        }
    }

    class UserViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {
            binding.userName.text = item.name
        }

        companion object {
            fun inflate(parent: ViewGroup) = UserViewHolder(
                ItemUserBinding.inflate(LayoutInflater.from(parent.context))
            )
        }
    }

    object UserDiffUtil: DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    class UserClickListener(private val click: (name: String) -> Unit) {
        fun onClick(name: String) = click(name)
    }
}
