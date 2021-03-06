package com.prasunmondal.mbros20.models

import com.google.gson.reflect.TypeToken
import com.prasunmondal.mbros20.database_calls.CustomerAddNew
import com.prasunmondal.mbros20.utils.DataParser
import java.io.Serializable
import java.util.*

class Customer : Serializable {
    var id: String
    var name: String
    var phoneNumber1: String
    var phoneNumber2: String
    var address: String

    constructor(
        id: String,
        name: String,
        phoneNumber1: String,
        phoneNumber2: String,
        address: String
    ) {
        this.id = id
        this.name = name
        this.phoneNumber1 = phoneNumber1
        this.phoneNumber2 = phoneNumber2
        this.address = address
    }

    fun addToDB() {
        CustomerAddNew {}.execute(this)
    }

    override fun toString(): String {
        return "Customer:: " +
                "\nid: $id" +
                "\nname: $name" +
                "\nphoneNumber1: $phoneNumber1" +
                "\nphoneNumber2: $phoneNumber2" +
                "\naddress: $address"
    }

    companion object {
        public fun parse(jsonString: String): ArrayList<Customer> {
            return DataParser.parseJSONObject(object :
                TypeToken<ArrayList<Customer>>() {}.type, jsonString, "records")
        }
    }
}