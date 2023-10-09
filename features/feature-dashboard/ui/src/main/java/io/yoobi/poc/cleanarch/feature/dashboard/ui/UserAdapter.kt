package io.yoobi.poc.cleanarch.feature.dashboard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.yoobi.poc.cleanarch.feature.dashboard.domain.model.UserDomainModel
import io.yoobi.poc.cleanarch.features.dashboard.ui.databinding.ItemUserBinding

class UserAdapter(private val clickListener: UserClickListener)
    : ListAdapter<UserDomainModel, UserAdapter.UserViewHolder>(UserDiffUtil) {

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

        fun bind(item: UserDomainModel) {
            binding.userName.text = item.name
        }

        companion object {
            fun inflate(parent: ViewGroup) = UserViewHolder(
                ItemUserBinding.inflate(LayoutInflater.from(parent.context))
            )
        }
    }

    object UserDiffUtil: DiffUtil.ItemCallback<UserDomainModel>() {
        override fun areItemsTheSame(oldItem: UserDomainModel, newItem: UserDomainModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: UserDomainModel, newItem: UserDomainModel): Boolean {
            return oldItem == newItem
        }
    }

    fun interface UserClickListener {
        fun onClick(name: String)
    }
}
