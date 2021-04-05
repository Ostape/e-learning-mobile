package com.robosh.basestartapplication.home.presenter

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robosh.basestartapplication.model.MovieEvent
import com.robosh.basestartapplication.model.MovieState
import com.robosh.basestartapplication.net.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    val intentChannel = Channel<MovieEvent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MovieState>(MovieState.LoadingState)
    val state: StateFlow<MovieState>
        get() = _state

    init {
        viewModelScope.launch {
            obtainEvent()
        }
    }

    @VisibleForTesting
    suspend fun obtainEvent() {
        intentChannel.consumeEach { movieEvent ->
            when (movieEvent) {
                is MovieEvent.MoviesFetch -> _state.value = getMoviesUseCase.execute()
                is MovieEvent.MovieClicked -> _state.value = MovieState.MovieClickedState(movieEvent.movie)
                is MovieEvent.MovieNotified -> Unit
            }
        }
    }
}