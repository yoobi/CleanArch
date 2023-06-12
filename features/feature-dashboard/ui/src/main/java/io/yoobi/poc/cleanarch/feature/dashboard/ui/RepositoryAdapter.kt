package io.yoobi.poc.cleanarch.feature.dashboard.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.yoobi.poc.cleanarch.common.view.RepositoryCustomView
import io.yoobi.poc.cleanarch.feature.repository.domain.model.model.RepositoryDomainModel

class RepositoryAdapter(private val clickListener: RepositoryListener)
    : ListAdapter<RepositoryDomainModel, RepositoryAdapter.RepositoryViewHolder>(RepositoryDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        getItem(position)?.let { repository ->
            holder.bind(repository)
            holder.itemView.setOnClickListener {
                clickListener.onClick(repository.authorName, repository.name)
            }
        }
    }

    class RepositoryViewHolder(private val view: RepositoryCustomView): RecyclerView.ViewHolder(view) {

        fun bind(item: RepositoryDomainModel) {
            view.init(item.name, item.authorName, item.star, item.authorAvatar)
        }

        companion object {
            fun inflate(parent: ViewGroup) = RepositoryViewHolder(
                RepositoryCustomView(parent.context)
            )
        }
    }

    object RepositoryDiffUtil: DiffUtil.ItemCallback<RepositoryDomainModel>() {
        override fun areItemsTheSame(oldItem: RepositoryDomainModel, newItem: RepositoryDomainModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: RepositoryDomainModel, newItem: RepositoryDomainModel): Boolean {
            return oldItem == newItem
        }
    }

    class RepositoryListener(val click: (owner: String, name: String) -> Unit) {
        fun onClick(owner: String, name: String) = click(owner, name)
    }
}
