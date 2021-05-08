package com.robosh.basestartapplication.wishlist.presenter

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robosh.basestartapplication.model.account.AccountEvent
import com.robosh.basestartapplication.model.account.AccountState
import com.robosh.basestartapplication.net.usecase.GetUserDataUseCase
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
class WishListViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {

    val intentChannel = Channel<AccountEvent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<AccountState>(AccountState.Idle)
    val state: StateFlow<AccountState>
        get() = _state

    init {
        viewModelScope.launch {
            obtainEvent()
        }
    }

    @VisibleForTesting
    suspend fun obtainEvent() {
        intentChannel.consumeEach { accountEvent ->
            when (accountEvent) {
                is AccountEvent.Loading -> {
                    _state.value = getUserDataUseCase.execute()
                }
            }
        }
    }
}