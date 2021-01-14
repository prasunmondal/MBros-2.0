package com.prasunmondal.mbros20.models

import android.content.Context
import com.prasunmondal.mbros20.Files.IOObjectToFile
import com.prasunmondal.mbros20.Utils.StringConstants
import java.io.Serializable
import java.util.ArrayList

class CustomerList: Serializable {
    companion object {
        lateinit var list: ArrayList<Customer>

        fun saveToFile(context: Context) {
            IOObjectToFile.WriteObjectToFile(context, StringConstants.FILENAME_CUSTOMER_REPO, CustomerList.list);
        }

        fun getFromFile(context: Context): ArrayList<Customer>? {
            var content = IOObjectToFile.ReadObjectFromFile(context, StringConstants.FILENAME_CUSTOMER_REPO)
            if(content != null) {
                list = content as ArrayList<Customer>
                return list
            }
            return null
        }
    }
}