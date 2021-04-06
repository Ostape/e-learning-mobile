package com.robosh.basestartapplication.detail.presenter

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robosh.basestartapplication.model.CourseEvent
import com.robosh.basestartapplication.model.CourseState
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

    val intentChannel = Channel<CourseEvent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<CourseState>(CourseState.LoadingState)
    val state: StateFlow<CourseState>
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
                is CourseEvent.CourseNotified -> {
                    _state.value = getOneMovieUseCase.execute(movieEvent.id)
                }
                is CourseEvent.CourseSubscribeClicked -> Unit
                is CourseEvent.CoursesFetch -> Unit
            }
        }
    }
}