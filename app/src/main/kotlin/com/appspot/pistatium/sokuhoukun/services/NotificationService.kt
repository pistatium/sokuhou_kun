package com.appspot.pistatium.sokuhoukun.services

import android.accessibilityservice.AccessibilityService
import android.app.Notification
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.widget.RemoteViews
import android.widget.TextView
import com.appspot.pistatium.sokuhoukun.models.Pref
import net.gpedro.integrations.slack.SlackApi
import net.gpedro.integrations.slack.SlackMessage


class NotificationService: AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.d("event", event.toString())
        event?.let  {
            val packageName = event.packageName;
            val action = event.action;
            val time = event.eventTime;
            val type = event.eventType;
            val text = event.text;

            if (packageName.contains("com.android")) {
                return
            }
            if (packageName.contains("com.google")) {
                return
            }
            Log.d("notification", packageName.toString())
            Log.d("notification", text.toString())
            event.parcelableData?.let {
                val notification = event.parcelableData as Notification
                val rv = notification.contentView as RemoteViews
                val view = rv.apply(this, null) as View
                val icon = view.findViewById(android.R.id.icon)
                val title = view.findViewById(android.R.id.title) as TextView
                val description = view.findViewById(android.R.id.text1) as TextView
                Log.d("notification", title.text.toString())
                Log.d("notification", description.text.toString())
                postToSlack(title.text.toString(), description.text.toString())
            }

        }
    }

    private fun postToSlack(app_name: String, text: String) {
        val pref = Pref(applicationContext)
        val api = SlackApi(pref.webhook)
        api.call(SlackMessage(app_name, text))
    }

    override fun onInterrupt() {}
}