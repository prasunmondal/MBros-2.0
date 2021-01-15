package com.prasunmondal.mbros20.models

import android.content.Context
import com.prasunmondal.mbros20.files.IOObjectToFile
import com.prasunmondal.mbros20.utils.StringConstants
import java.io.Serializable
import java.util.ArrayList

class CustomerList: Serializable {
    companion object {
        lateinit var list: ArrayList<Customer>

        fun saveToFile(context: Context) {
            IOObjectToFile.WriteObjectToFile(context, StringConstants.FILENAME_CUSTOMER_REPO, CustomerList.list)
            getFromFile(context)
        }

        fun getFromFile(context: Context): ArrayList<Customer>? {
            var content = IOObjectToFile.ReadObjectFromFile(context, StringConstants.FILENAME_CUSTOMER_REPO)
            if(content != null) {
                list = content as ArrayList<Customer>
                return list
            }
            return null
        }

        fun getCustomerById(id: String): Customer? {

            for(customer in list) {
                if(customer.id.equals(id, true)) {
                    return customer
                }
            }
            return null;
        }
    }
}