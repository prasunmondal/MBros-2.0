package com.prasunmondal.mbros20.models

import android.content.Context
import com.prasunmondal.mbros20.files.IOObjectToFile
import com.prasunmondal.mbros20.utils.StringConstants
import java.io.Serializable
import java.util.ArrayList

class OrderList: Serializable {

    private constructor()
    var list: ArrayList<Order> = arrayListOf()

    fun saveToFile(context: Context, orderList: ArrayList<Order>) {
        IOObjectToFile.WriteObjectToFile(context, StringConstants.FILENAME_TODAYS_ORDER, orderList)
        getFromFile(context)
    }

    fun getFromFile(context: Context): ArrayList<Order>? {
        val content = IOObjectToFile.ReadObjectFromFile(context, StringConstants.FILENAME_TODAYS_ORDER)
        if(content != null) {
            list = content as ArrayList<Order>
            return list
        }
        return null
    }

    companion object {
        val instance = OrderList()
    }
}