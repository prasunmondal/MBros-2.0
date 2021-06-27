package com.prasunmondal.mbros20.models.persons.customer

import android.content.Context
import com.google.gson.reflect.TypeToken
import com.prasunmondal.mbros20.models.persons.delivery_man.AppUser
import com.prasunmondal.mbros20.utils.FileUtils
import com.prasunmondal.mbros20.utils.StringConstants
import com.prasunmondal.postjsontosheets.clients.get.Get
import com.prasunmondal.postjsontosheets.clients.post.serializable.PostObject
import java.io.Serializable
import java.lang.Exception

class Customer: Serializable {
    var name: String
    var phoneNumber1: String
    var phoneNumber2: String
    var email: String

    constructor(name: String, phoneNumber1: String = "", phoneNumber2: String = "", email: String = "") {
        this.name = name
        this.phoneNumber1 = phoneNumber1
        this.phoneNumber2 = phoneNumber2
        this.email = email
    }

    companion object {
        var saveInFile = "MBros20.Customer"
        fun getDetails(name: String): AppUser {
            // TODO: Try to read from cache
            val call = Get.builder()
                .scriptId(StringConstants.DB_SCRIPT_URL)
                .sheetId(StringConstants.DB_SHEET_ID)
                .tabName(StringConstants.DB_TABNAME_CUSTOMER)
                .conditionAnd("name", name)
                .build()

            val response = call.execute()
            // TODO: Write to cache

            val type = object : TypeToken<List<AppUser>>() {}.type
            val p: AppUser = response.getParsedList<AppUser>(type)[0]
            return p
        }

        fun fetchActiveCustomers(context: Context): List<Customer> {
            // Try to Read from cache
            try {
                val customerList = FileUtils.read<List<Customer>>(context, saveInFile)
                println("Cache Hit.")
                return customerList
            } catch (e: Exception) {
                println("Cache Miss.")
            }

            // Fetch from server
            println("Fetching Data from server.")
            val call = Get.builder()
                .scriptId(StringConstants.DB_SCRIPT_URL)
                .sheetId(StringConstants.DB_SHEET_ID)
                .tabName(StringConstants.DB_TABNAME_CUSTOMER)
                .conditionAnd("status", Customer_Status.ACTIVE.name)
                .build()

            val response = call.execute()
            val type = object : TypeToken<List<Customer>>() {}.type
            val customerList = response.getParsedList<Customer>(type)

            println("Writing to Cache.")
            FileUtils.write(context, saveInFile, customerList)

            return customerList
        }

        fun saveCustomerDetails(customer: Customer) {
            val call = PostObject.builder()
                .scriptId(StringConstants.DB_SCRIPT_URL)
                .sheetId(StringConstants.DB_SHEET_ID)
                .tabName(StringConstants.DB_TABNAME_CUSTOMER)
                .dataObject(customer as Object)
                .uniqueColumn("name")
                .build()
            call.execute()
        }
    }

    override fun toString(): String {
        return "Customer(name='$name', phoneNumber1='$phoneNumber1', phoneNumber2='$phoneNumber2', email='$email')"
    }
}