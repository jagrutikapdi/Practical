package com.practical.data.api

import com.practical.data.model.NewsData

interface ApiHelper {
    suspend fun getData(): NewsData
}