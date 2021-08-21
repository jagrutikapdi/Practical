package com.practical.ui.main.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practical.data.repository.MainRepository
import com.practical.ui.main.intent.MainIntent
import com.practical.ui.main.viewstate.MainState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel(
    private val repository: MainRepository,activity: Activity
) : ViewModel() {

   public val ticketIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            ticketIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchTickets ->fetchData ()
                }
            }
        }
    }


    private fun fetchData() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.News(repository.getData())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }
}