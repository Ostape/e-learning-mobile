package com.robosh.basestartapplication.courses.presenter

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robosh.basestartapplication.model.CourseEvent
import com.robosh.basestartapplication.model.CourseState
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
class CoursesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
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
                is CourseEvent.CoursesFetch -> _state.value = getMoviesUseCase.execute()
                is CourseEvent.CourseSubscribeClicked -> _state.value =
                    CourseState.CourseSubscribeClickedState(movieEvent.course)
                is CourseEvent.CourseNotified -> Unit
                is CourseEvent.CourseDetailClicked -> _state.value =
                    CourseState.CourseDetailClickedState(movieEvent.course)
                is CourseEvent.CoursesIdle -> _state.value = CourseState.Idle
            }
        }
    }
}