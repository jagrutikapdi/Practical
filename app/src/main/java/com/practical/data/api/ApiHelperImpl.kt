package com.practical.data.api

import android.app.Activity
import com.practical.data.model.NewsData

class ApiHelperImpl(private val apiService: ApiService,private val activity: Activity) : ApiHelper {
    override suspend fun getData(): NewsData {
        return apiService.getData()
    }
}