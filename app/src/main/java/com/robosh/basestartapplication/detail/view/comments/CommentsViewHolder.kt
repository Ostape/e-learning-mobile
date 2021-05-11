package com.robosh.basestartapplication.detail.view.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robosh.basestartapplication.R
import com.robosh.basestartapplication.databinding.ViewHolderCommentsBinding
import com.robosh.basestartapplication.model.Comment
import com.squareup.picasso.Picasso

class CommentsViewHolder private constructor(
    view: View
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun create(
            parent: ViewGroup
        ): CommentsViewHolder {
            return CommentsViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_comments, parent, false)
            )
        }
    }

    private val binding: ViewHolderCommentsBinding = ViewHolderCommentsBinding.bind(view)

    fun bind(comment: Comment) {
        with(binding) {
//            userName.text = comment.nickname
            commentText.text = comment.text
        }
    }
}