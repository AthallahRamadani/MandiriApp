package com.athallah.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.athallah.newsapp.data.model.ArticlesItem
import com.athallah.newsapp.databinding.ItemNewsBinding
import com.bumptech.glide.Glide

class HeadlineAdapter(private var articles: List<ArticlesItem>)
    : RecyclerView.Adapter<HeadlineAdapter.RecyclerViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeadlineAdapter.RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeadlineAdapter.RecyclerViewHolder, position: Int) {
        val currentArticle = articles[position]

        holder.itemTitle.text = currentArticle.title
        holder.itemSource.text = currentArticle.source.name
        holder.itemDate.text = currentArticle.publishedAt.substring(0,10)

        Glide
            .with(holder.itemView.context)
            .load(currentArticle.urlToImage)
            .into(holder.itemImage)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun updateData(newArticles: List<ArticlesItem>) {
        articles = newArticles
        notifyDataSetChanged()
    }

    inner class RecyclerViewHolder(binding : ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val itemImage = binding.ivHeadlineNews
        val itemTitle = binding.tvTitleHeadlineNews
        val itemSource = binding.tvSource
        val itemDate = binding.tvDate

    }
}