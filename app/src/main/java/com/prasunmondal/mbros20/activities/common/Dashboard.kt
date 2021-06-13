package com.prasunmondal.mbros20.activities.common

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.prasunmondal.mbros20.R
import android.graphics.Color;
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams;


class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        var outer_most_container = findViewById<LinearLayout>(R.id.dashboard_outer_most_container)
        CreateCardViewProgrammatically(outer_most_container, this)
    }

    fun CreateCardViewProgrammatically(container: LinearLayout, context: Context) {
        var cardview = CardView(context)
        var layoutparams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        cardview.setLayoutParams(layoutparams)
        cardview.setRadius(15F)
        cardview.setPadding(25, 25, 25, 25)
        cardview.setCardBackgroundColor(Color.RED)
        cardview.setMaxCardElevation(30F)
        cardview.setMaxCardElevation(6F)

        var textview = TextView(context)
        textview.setLayoutParams(layoutparams)
        textview.setText("CardView Programmatically")
        textview.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13F)
        textview.setTextColor(Color.WHITE)
        textview.setPadding(25, 25, 25, 25)
        textview.setGravity(Gravity.CENTER)
        cardview.addView(textview)


        container.addView(cardview)
    }
}