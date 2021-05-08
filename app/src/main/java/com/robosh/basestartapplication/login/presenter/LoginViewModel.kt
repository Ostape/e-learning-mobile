package com.robosh.basestartapplication.login.presenter

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robosh.basestartapplication.model.login.LoginEvent
import com.robosh.basestartapplication.model.login.LoginState
import com.robosh.basestartapplication.net.usecase.AuthUseCase
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
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    val intentChannel = Channel<LoginEvent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<LoginState>(LoginState.LoginIdle)
    val state: StateFlow<LoginState>
        get() = _state

    init {
        viewModelScope.launch {
            obtainEvent()
        }
    }

    @VisibleForTesting
    suspend fun obtainEvent() {
        intentChannel.consumeEach { loginEvent ->
            when (loginEvent) {
                is LoginEvent.UserLoginClicked -> {
                    _state.value = authUseCase.execute(
                        loginEvent.username,
                        loginEvent.password
                    )
                }
                is LoginEvent.UpdateEvent -> _state.value = LoginState.LoginIdle
            }
        }
    }
}