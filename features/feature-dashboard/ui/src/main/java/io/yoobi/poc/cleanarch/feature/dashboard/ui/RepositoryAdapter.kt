package io.yoobi.poc.cleanarch.feature.dashboard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.yoobi.poc.cleanarch.feature.dashboard.domain.model.Repository
import io.yoobi.poc.cleanarch.features.dashboard.ui.databinding.ItemRepositoryBinding

class RepositoryAdapter(private val clickListener: RepositoryListener)
    : ListAdapter<Repository, RepositoryAdapter.RepositoryViewHolder>(RepositoryDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        getItem(position)?.let { repository ->
            holder.bind(repository)
            holder.itemView.setOnClickListener {
                clickListener.onClick(repository.author.name, repository.name)
            }
        }
    }

    class RepositoryViewHolder(private val binding: ItemRepositoryBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Repository) {
            binding.repositoryName.text = item.name
            binding.repositoryAuthor.text = item.author.name
            binding.repositoryStars.text = "Stars: ${item.star}"
            Glide.with(binding.repositoryImage)
                .load(item.author.photo)
                .into(binding.repositoryImage)
        }

        companion object {
            fun inflate(parent: ViewGroup) = RepositoryViewHolder(
                ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context))
            )
        }
    }

    object RepositoryDiffUtil: DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem == newItem
        }
    }

    class RepositoryListener(val click: (owner: String, name: String) -> Unit) {
        fun onClick(owner: String, name: String) = click(owner, name)
    }
}
