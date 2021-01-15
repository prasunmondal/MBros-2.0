package com.prasunmondal.mbros20.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.mbros20.PrepareAppDataFetch
import com.prasunmondal.mbros20.R
import com.prasunmondal.mbros20.models.EnumLoggedInUser
import com.prasunmondal.mbros20.session_data.SessionData

class UserRoleSelect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_role_select)
    }

    fun onClickUserRoleSelectDeliver(view: View) {
        SessionData.instance.loggedInUserRole = EnumLoggedInUser.DELIVERY_MAN
        val myIntent = Intent(this, PrepareAppDataFetch::class.java)
        this.startActivity(myIntent)
        finish()
    }

    fun onClickUserRoleSelectManager(view: View) {
        SessionData.instance.loggedInUserRole = EnumLoggedInUser.MANAGER
        val myIntent = Intent(this, SelectActivityManager::class.java)
        this.startActivity(myIntent)
        finish()
    }
}