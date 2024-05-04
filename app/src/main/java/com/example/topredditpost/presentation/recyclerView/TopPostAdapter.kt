package com.example.topredditpost.presentation.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.topredditpost.R
import com.example.topredditpost.databinding.PostItemBinding
import com.example.topredditpost.domain.entity.Post
import com.squareup.picasso.Picasso

class TopPostAdapter : ListAdapter<Post, TopPostViewHolder>(TopPostDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPostViewHolder {
        val view = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopPostViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopPostViewHolder, position: Int) {
        val post = getItem(position)
        with(post) {
            holder.author.text = author
            holder.numComment.text = numComment
            holder.publicTime.text = time
            if (img!!.contains(URL_REGEX)) {
                holder.imagePost.visibility = View.VISIBLE
                Picasso.get().load(img).into(holder.imagePost)
            } else {
                holder.imagePost.visibility = View.GONE
            }
        }
    }



    companion object {
        val URL_REGEX = """https://[a-z]\.thumbs\.redditmedia\.com/""".toRegex()
    }
}