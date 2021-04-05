package com.robosh.basestartapplication.home.view

import com.robosh.basestartapplication.model.Movie

interface MovieClickCallback {

    fun onMovieRemindClicked(movie: Movie)
}