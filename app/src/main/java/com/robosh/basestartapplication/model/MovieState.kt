package com.robosh.basestartapplication.model

sealed class MovieState {
    object Idle : MovieState()
    object LoadingState : MovieState()
    data class DataListState(val data: List<Movie>) : MovieState()
    data class ErrorState(val data: String) : MovieState()
    data class SingleDataState(val movie: Movie) : MovieState()
    data class MovieClickedState(val movie: Movie) : MovieState()
}