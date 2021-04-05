package com.robosh.basestartapplication.detail.presenter

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robosh.basestartapplication.model.MovieEvent
import com.robosh.basestartapplication.model.MovieState
import com.robosh.basestartapplication.net.usecase.GetOneMovieUseCase
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
class DetailViewModel @Inject constructor(
    private val getOneMovieUseCase: GetOneMovieUseCase
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
                is MovieEvent.MovieNotified -> {
                    _state.value = getOneMovieUseCase.execute(movieEvent.id)
                }
                is MovieEvent.MovieClicked -> Unit
                is MovieEvent.MoviesFetch -> Unit
            }
        }
    }
}