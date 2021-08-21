package com.practical.util

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.practical.R

class Utils {
    companion object {
        fun showErrorSnackBar(activity: Activity, message: String) {
            try {
                val snack =
                    Snackbar.make(activity.window.decorView.rootView, message, Snackbar.LENGTH_LONG)
                val sbview = snack.view
                sbview.setBackgroundColor(
                    ContextCompat.getColor(
                        activity,
                        android.R.color.holo_red_dark
                    )
                )
                val textView = sbview.findViewById<View>(R.id.snackbar_text) as TextView
                textView.setTextColor(ContextCompat.getColor(activity, android.R.color.white))
                snack.show()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}