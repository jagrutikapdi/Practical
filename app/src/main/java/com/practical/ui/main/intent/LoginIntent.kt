package com.practical.ui.main.intent

sealed class LoginIntent {

    object FetchUser : LoginIntent()

}