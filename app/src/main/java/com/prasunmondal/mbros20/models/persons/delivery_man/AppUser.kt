package com.prasunmondal.mbros20.models.persons.delivery_man

import com.prasunmondal.mbros20.utils.StringConstants
import com.prasunmondal.postjsontosheets.clients.get.Get
import com.prasunmondal.postjsontosheets.clients.post.serializable.PostObject
import com.google.gson.reflect.TypeToken
import com.prasunmondal.mbros20.models.persons.AccessStatuses
import com.prasunmondal.mbros20.models.persons.AppAccessRoles
import com.prasunmondal.postjsontosheets.clients.commons.APIResponse
import com.prasunmondal.postjsontosheets.clients.post.serializable.PostObjectResponse


class AppUser {
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

    constructor(name: String, phoneUID: String) {
        this.name = name
        this.role = AppAccessRoles.NONE
        this.accessStatus = AccessStatuses.APP_OPEN
        this.phoneUID = phoneUID
    }

    companion object {
        fun getDetails(phoneUID: String): AppUser? {
            // TODO: Try to read from cache
            val call = Get.builder()
                .scriptId(StringConstants.DB_SCRIPT_URL)
                .sheetId(StringConstants.DB_SHEET_ID)
                .tabName(StringConstants.DB_TABNAME_PERSON)
                .conditionAnd("phoneUID", phoneUID)
                .build()

            val response = call.execute()
            // TODO: Write to cache

            val type = object : TypeToken<List<AppUser>>() {}.type
            val p = response.getParsedList<AppUser>(type)
            if(p.isEmpty())
                return null
            return p[0]
        }

        fun saveDetails(person: AppUser): PostObjectResponse {
            val call = PostObject.builder()
                .scriptId(StringConstants.DB_SCRIPT_URL)
                .sheetId(StringConstants.DB_SHEET_ID)
                .tabName(StringConstants.DB_TABNAME_PERSON)
                .dataObject(person as Object)
                .uniqueColumn("phoneUID")
                .build()
            return call.execute()
        }
    }

    override fun toString(): String {
        return "Person(name='$name', role='$role', accessStatus='$accessStatus', phoneUID='$phoneUID')"
    }
}