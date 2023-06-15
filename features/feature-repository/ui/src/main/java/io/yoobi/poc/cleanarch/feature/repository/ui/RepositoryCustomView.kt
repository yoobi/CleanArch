package io.yoobi.poc.cleanarch.feature.repository.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import io.yoobi.poc.cleanarch.features.repository.R
import io.yoobi.poc.cleanarch.features.repository.databinding.ItemRepositoryBinding

class RepositoryCustomView: LinearLayout {

    private val binding: ItemRepositoryBinding

    constructor(context: Context): super(context) {
        binding = inflateView()
    }

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {
        binding = inflateView()
    }

    private fun inflateView(): ItemRepositoryBinding =
        ItemRepositoryBinding.inflate(LayoutInflater.from(context)).also { addView(it.root) }

    fun init(name: String, author: String, stars: Int, authorAvatar: String) {
        binding.repositoryName.text = name
        binding.repositoryAuthor.text = author
        binding.repositoryStars.text = context.getString(R.string.item_repository_stars, stars)
        Glide.with(binding.repositoryImage)
            .load(authorAvatar)
            .into(binding.repositoryImage)
    }

}
