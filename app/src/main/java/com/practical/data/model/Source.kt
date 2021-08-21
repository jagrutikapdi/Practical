package com.practical.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Source :Serializable{

	@SerializedName("id")
	val id: String =""
	@SerializedName("name")
	val name: String=""
}