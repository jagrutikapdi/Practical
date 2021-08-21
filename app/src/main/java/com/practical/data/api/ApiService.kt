package com.practical.data.api

import com.practical.data.model.NewsData
import retrofit2.http.*
@JvmSuppressWildcards
interface ApiService {

   @JvmSuppressWildcards
   @GET("top-headlines?sources=google-news&apiKey=9a0c8e375ada4198a26f7a52638c4b78")
   suspend fun getData( ): NewsData
}