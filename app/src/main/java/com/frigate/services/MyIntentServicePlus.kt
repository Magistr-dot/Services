package com.frigate.services

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log

class MyIntentServicePlus : IntentService(NAME) {

    override fun onCreate() {
        super.onCreate()
        log("On create")


    }


    override fun onDestroy() {
        log("On Destroy")
        setIntentRedelivery(true)
        super.onDestroy()
    }


    override fun onHandleIntent(p0: Intent?) {
        log("On Handle Intent")
        val page = p0?.getIntExtra(PAGE, 0) ?: 0
        for (i in 0 until 100) {
            Thread.sleep(1000)
            log("Timer $i $page")
        }
    }

    private fun log(mes: String) {
        Log.d("Service", "My intent service: $mes")
    }

    companion object {
        private const val NAME = "MyIntentService"
        private const val PAGE = "page"

        fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, MyIntentServicePlus::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }
}