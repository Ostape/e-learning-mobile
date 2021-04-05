package com.robosh.basestartapplication.home.view

import android.view.View
import com.robosh.basestartapplication.model.Movie

class MovieClickListenerFactoryImpl constructor(
    private val callback: MovieClickCallback
) : MovieClickListenerFactory {

    override fun createOnClickListener(movie: Movie): View.OnClickListener {
        return View.OnClickListener {
            callback.onMovieRemindClicked(movie)
        }
    }
}