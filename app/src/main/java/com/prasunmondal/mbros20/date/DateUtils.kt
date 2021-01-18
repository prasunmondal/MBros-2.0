package com.prasunmondal.mbros20.date

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {
        fun getDate(): String {
            val c: Date = Calendar.getInstance().getTime()
            println("Current time => $c")

            val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
            val formattedDate: String = df.format(c)
            return formattedDate
        }

        fun getTime(): String {
            val c: Date = Calendar.getInstance().getTime()
            println("Current time => $c")

            val df = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            val formattedDate: String = df.format(c)
            return formattedDate
        }
    }
}