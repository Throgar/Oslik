package com.example.oslik.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.oslik.data.ArticleThumbnail
import com.example.oslik.databinding.ArticleListItemBinding

class ArticleListAdapter: ListAdapter<ArticleThumbnail, RecyclerView.ViewHolder>(ArticleDiffCallBack()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArticleViewHolder(ArticleListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val article = getItem(position)
        (holder as ArticleViewHolder).bind(article)
    }

    class ArticleViewHolder(
        val binding: ArticleListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                binding.article?.let{item ->
                    navigateToArticle(item, it)
                }
            }
        }

        fun bind(item: ArticleThumbnail) {
            binding.article = item
            binding.executePendingBindings()
        }

        fun navigateToArticle(item: ArticleThumbnail, view: View) {
            //TODO navigation
        }

    }

    private class ArticleDiffCallBack: DiffUtil.ItemCallback<ArticleThumbnail>(){
        override fun areItemsTheSame(
            oldItem: ArticleThumbnail,
            newItem: ArticleThumbnail
        ): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: ArticleThumbnail,
            newItem: ArticleThumbnail
        ): Boolean {
            return oldItem == newItem
        }
    }

}
