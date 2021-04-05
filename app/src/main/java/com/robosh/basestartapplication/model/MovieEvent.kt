package com.robosh.basestartapplication.model

sealed class MovieEvent {
    object MoviesFetch : MovieEvent()
    class MovieClicked(val movie: Movie) : MovieEvent()
    class MovieNotified(val id: Int) : MovieEvent()
}