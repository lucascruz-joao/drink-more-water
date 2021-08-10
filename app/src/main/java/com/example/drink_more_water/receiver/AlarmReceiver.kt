package com.example.drink_more_water.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.drink_more_water.R
import com.example.drink_more_water.util.sendNotification

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // TODO: Step 1.10 [Optional] remove toast
        //Toast.makeText(context, context.getText(R.string.drink_now), Toast.LENGTH_SHORT).show()

        val notificationManager = ContextCompat.getSystemService(
                context,
                NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(
                context.getText(R.string.drink_now).toString(),
                context
        )
    }
}