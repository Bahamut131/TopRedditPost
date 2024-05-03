package com.example.topredditpost.presentation.recyclerView

import androidx.recyclerview.widget.RecyclerView
import com.example.topredditpost.databinding.PostItemBinding

class TopPostViewHolder(binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val author = binding.author
    val numComment = binding.numComments
    val imagePost = binding.imagePost
    val publicTime = binding.publicTime
}