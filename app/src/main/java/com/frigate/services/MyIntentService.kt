package com.frigate.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.*

class MyIntentService : IntentService(NAME) {

    override fun onCreate() {
        super.onCreate()
        log("On create")
        createNotificationChannel()
        startForeground(NOTIFICATION, createNotification())

    }


    private fun createNotificationChannel() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Title")
            .setContentText("Text")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()
    }

    override fun onDestroy() {
        log("On Destroy")
        super.onDestroy()
    }


    override fun onHandleIntent(p0: Intent?) {
        log("On Handle Intent")
        for (i in 0 until 100) {
            Thread.sleep(1000)
            log("Timer $i")
        }
    }

    private fun log(mes: String) {
        Log.d("Service", "My intent service: $mes")
    }

    companion object {

        private const val CHANNEL_ID = "id"
        private const val CHANNEL_NAME = "name"
        private const val NOTIFICATION = 1
        private const val NAME = "MyIntentService"


        fun newIntent(context: Context): Intent {
            return Intent(context, MyIntentService::class.java)
        }
    }
}