package com.prasunmondal.mbros20.session_data

import com.prasunmondal.mbros20.models.EnumLoggedInUser

class SessionData {

    lateinit var loggedInUserRole: EnumLoggedInUser

    companion object {
        val instance = SessionData()
    }
}