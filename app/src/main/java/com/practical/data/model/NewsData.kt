package com.practical.data.model
import com.google.gson.annotations.SerializedName
import com.practical.data.model.Articles


data class NewsData (

	@SerializedName("status") val status : String,
	@SerializedName("totalResults") val totalResults : Int,
	@SerializedName("articles") val articles : List<Articles>
)