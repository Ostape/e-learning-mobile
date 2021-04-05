package com.robosh.basestartapplication.home.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robosh.basestartapplication.model.Movie

class MovieAdapter(
    private val clickListener: MovieClickListenerFactory
) : RecyclerView.Adapter<MovieViewHolder>() {

    private val movies = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.create(parent, clickListener)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun setData(list: List<Movie>) {
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }
}