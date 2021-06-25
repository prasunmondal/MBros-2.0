package com.prasunmondal.mbros20.models.persons.delivery_man

import com.prasunmondal.mbros20.models.persons.AccessStatuses
import com.prasunmondal.mbros20.models.persons.AppAccessRoles
import com.prasunmondal.mbros20.utils.StringConstants
import com.prasunmondal.postjsontosheets.clients.get.Get
import com.prasunmondal.postjsontosheets.clients.post.serializable.PostObject

class Person {
    lateinit var name: String
    lateinit var role: AppAccessRoles
    lateinit var accessStatus: AccessStatuses
    lateinit var phoneUID: String

    constructor(
        name: String, role: AppAccessRoles,
        accessStatus: AccessStatuses, phoneUID: String) {
        this.name = name
        this.role = role
        this.accessStatus = accessStatus
        this.phoneUID = phoneUID
    }

    constructor(name: String, role: AppAccessRoles, phoneUID: String) {
        this.name = name
        this.role = role
        this.phoneUID = phoneUID
    }

    fun getDetails(phoneUID: String): Person {
        // TODO: Try to read from cache
        var call = Get.builder()
            .scriptId(StringConstants.DB_SCRIPT_URL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TABNAME_PERSON)
            .build()

        val response = call.execute()

        // TODO: Write to cache
        return response.getParsedList<Person>()[0] as Person
    }

    fun saveDetails(person: Person) {
        var call = PostObject.builder()
            .scriptId(StringConstants.DB_SCRIPT_URL)
            .sheetId(StringConstants.DB_SHEET_ID)
            .tabName(StringConstants.DB_TABNAME_PERSON)
            .dataObject(person as Object)
            .build()
        call.execute()
    }
}