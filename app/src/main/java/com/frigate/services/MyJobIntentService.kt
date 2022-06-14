package com.frigate.services

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

class MyJobIntentService : JobIntentService() {

    override fun onCreate() {
        super.onCreate()
        log("On create")
    }


    override fun onDestroy() {
        log("On Destroy")
        super.onDestroy()
    }


    override fun onHandleWork(intent: Intent) {
        log("On Handle Work Intent")
        val page = intent.getIntExtra(PAGE, 0)
        for (i in 0 until 100) {
            Thread.sleep(1000)
            log("Timer $i $page")
        }
    }

    private fun log(mes: String) {
        Log.d("Service", "My intent service: $mes")
    }

    companion object {
        private const val PAGE = "page"
        private const val JOB_ID = 123

        fun enqueue(context: Context, page: Int) {
            enqueueWork(
                context,
                MyJobService::class.java,
                JOB_ID,
                newIntent(context, page)
            )

        }

        private fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, MyJobIntentService::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }
}