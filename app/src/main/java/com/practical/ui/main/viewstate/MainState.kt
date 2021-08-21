package com.practical.ui.main.viewstate

import com.practical.data.model.NewsData


sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class News(val data: NewsData) : MainState()
    data class Error(val error: String?) : MainState()

}