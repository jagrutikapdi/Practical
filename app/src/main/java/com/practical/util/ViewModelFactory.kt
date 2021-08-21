package com.practical.util

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practical.data.api.ApiHelper
import com.practical.data.repository.MainRepository
import com.practical.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper, private val activity: Activity) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper),activity) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}