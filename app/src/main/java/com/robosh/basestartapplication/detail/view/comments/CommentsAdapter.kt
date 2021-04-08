package com.robosh.basestartapplication.detail.view.comments

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robosh.basestartapplication.model.Comment

class CommentsAdapter : RecyclerView.Adapter<CommentsViewHolder>() {

    private val comments = ArrayList<Comment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder.create(parent)
    }

    override fun getItemCount() = comments.size

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    fun setData(list: List<Comment>) {
        comments.clear()
        comments.addAll(list)
        notifyDataSetChanged()
    }
}