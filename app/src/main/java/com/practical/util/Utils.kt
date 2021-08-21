package com.practical.util

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.practical.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

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

        fun convertDate( dateStr:String):String{
            var dateConvert =  dateStr.substring(0, 19).replace("T"," ");
            val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val date: Date =
                dateFormat.parse(dateConvert) //You will get date object relative to server/client timezone wherever it is parsed
            val formatter: DateFormat =
                SimpleDateFormat("dd MMM, yyyy hh:mm a") //If you need time just put specific format for time like 'HH:mm:ss'

            return formatter.format(date)

        }
    }
}