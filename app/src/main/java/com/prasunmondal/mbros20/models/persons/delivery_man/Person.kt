package com.prasunmondal.mbros20.models.persons.delivery_man

import android.util.Log
import com.prasunmondal.mbros20.utils.StringConstants
import com.prasunmondal.postjsontosheets.clients.get.Get
import com.prasunmondal.postjsontosheets.clients.post.serializable.PostObject
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.prasunmondal.postjsontosheets.clients.commons.JSONUtils
import java.lang.reflect.Type
import java.util.*


class Person {
    lateinit var name: String
    lateinit var role: String
    lateinit var accessStatus: String
    lateinit var phoneUID: String



    constructor(
        name: String, role: String,
        accessStatus: String, phoneUID: String) {
        this.name = name
        this.role = role
        this.accessStatus = accessStatus
        this.phoneUID = phoneUID
    }

    constructor(name: String, role: String, phoneUID: String) {
        this.name = name
        this.role = role
        this.phoneUID = phoneUID
    }

    companion object {
        fun getDetails(phoneUID: String): Person {
            // TODO: Try to read from cache
            val call = Get.builder()
                .scriptId(StringConstants.DB_SCRIPT_URL)
                .sheetId(StringConstants.DB_SHEET_ID)
                .tabName(StringConstants.DB_TABNAME_PERSON)
                .conditionAnd("phoneUID", phoneUID)
                .build()

            val response = call.execute()
            // TODO: Write to cache

            val type = object : TypeToken<List<Person>>() {}.type
            val p: Person = response.getParsedList<Person>(type)[0]
            return p
        }

        fun saveDetails(person: Person) {
            val call = PostObject.builder()
                .scriptId(StringConstants.DB_SCRIPT_URL)
                .sheetId(StringConstants.DB_SHEET_ID)
                .tabName(StringConstants.DB_TABNAME_PERSON)
                .dataObject(person as Object)
                .uniqueColumn("phoneUID")
                .build()
            call.execute()
        }
    }

    override fun toString(): String {
        return "Person(name='$name', role='$role', accessStatus='$accessStatus', phoneUID='$phoneUID')"
    }
}