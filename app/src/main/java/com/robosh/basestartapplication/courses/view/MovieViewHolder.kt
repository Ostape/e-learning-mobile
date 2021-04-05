package com.robosh.basestartapplication.courses.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robosh.basestartapplication.R
import com.robosh.basestartapplication.databinding.ViewHolderMovieBinding
import com.robosh.basestartapplication.model.Movie
import com.robosh.basestartapplication.net.api.MovieDbApi.Companion.IMAGE_BASE_URL
import com.squareup.picasso.Picasso

class MovieViewHolder private constructor(
    view: View,
    private val clickListener: MovieClickListenerFactory
) : RecyclerView.ViewHolder(view) {

    companion object {
        fun create(
            parent: ViewGroup,
            clickListener: MovieClickListenerFactory
        ): MovieViewHolder {
            return MovieViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_movie, parent, false),
                clickListener
            )
        }
    }

    private val binding: ViewHolderMovieBinding = ViewHolderMovieBinding.bind(view)

    fun bind(movie: Movie) {
        with(binding) {
            Picasso.get().load(IMAGE_BASE_URL + movie.posterUrl).into(movieImage)
            movieTitle.text = movie.title
            movieDescription.text = movie.description
            movieDate.text = movie.date
            remindButton.setOnClickListener(clickListener.createOnClickListener(movie))
        }
    }
}