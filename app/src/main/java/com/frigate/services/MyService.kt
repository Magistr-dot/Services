package com.frigate.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyService : Service() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate() {
        super.onCreate()
        log("On create")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("On start")
        coroutineScope.launch {
            for (i in 0 until 100) {
                delay(1000)
                log("Timer $i")
            }
            stopSelf()
        }

        return super.onStartCommand(intent, flags, startId)

    }

    override fun onDestroy() {
        log("On Destroy")
        coroutineScope.cancel()
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    private fun log(mes: String) {
        Log.d("Service", "My service: $mes")
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MyService::class.java)
        }
    }
}