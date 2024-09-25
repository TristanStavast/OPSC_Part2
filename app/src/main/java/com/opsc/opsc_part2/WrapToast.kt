package com.opsc.opsc_part2

import android.app.Activity
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast

//Setting up custom toast
fun Toast.showCustomToast(message : String, activity: Activity)
{
    val layout = activity.layoutInflater.inflate(
        R.layout.custom_toast, activity.findViewById(R.id.toast_container)
    )

    val txtView = layout.findViewById<TextView>(R.id.toastText)
    txtView.text = message

    this.apply {
        setGravity(Gravity.BOTTOM, 0, 40)
        duration = Toast.LENGTH_SHORT
        view = layout
        show()
    }
}