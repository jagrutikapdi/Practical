package com.practical.util

import android.content.Context
import android.content.SharedPreferences


class Prefs private constructor(private val context: Context) {
    private val SP_NAME = this.javaClass.getPackage()!!.name
    private var sharedPreferences: SharedPreferences? = null
    private var preferenceScreen: SharedPreferences? = null



    var accessToken: String?
        get() = sharedPreferences?.getString("access_token", "")
        set(accessToken) {
            sharedPreferences!!.edit().putString("access_token", accessToken).commit()
        }


    init {
        sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)

        preferenceScreen = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)

    }

    fun clearPrefs() {
        sharedPreferences!!.edit().clear().apply()
    }




    companion object {
        private var instance: Prefs? = null

        fun getInstance(context: Context?): Prefs {
            if (instance == null)  // NOT thread safe!
                instance = Prefs(context!!)

            return instance!!
        }
    }


}
