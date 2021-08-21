package com.practical.data.repository

import com.practical.data.api.ApiHelper


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getData() = apiHelper.getData()

}