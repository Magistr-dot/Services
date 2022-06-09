package com.frigate.services

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.Build
import android.util.Log
import kotlinx.coroutines.*

class MyJobService : JobService() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate() {
        super.onCreate()
        log("On create")
    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        log("On start JOB")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            coroutineScope.launch {
                var workItem = p0?.dequeueWork()
                while (workItem != null) {
                    val page = workItem.intent.getIntExtra(PAGE, 0)

                    for (i in 0 until 5) {
                        delay(1000)
                        log("Timer $i $page")
                    }
                    p0?.completeWork(workItem)
                    workItem = p0?.dequeueWork()
                }
                jobFinished(p0, false)
            }
        }
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        log("On stop JOB")
        return true
    }


    override fun onDestroy() {
        log("On Destroy")
        coroutineScope.cancel()
        super.onDestroy()
    }

    private fun log(mes: String) {
        Log.d("Service", "My service: $mes")
    }

    companion object {
        const val JOB_ID = 1
        private const val PAGE = "page"

        fun newIntent(page: Int): Intent {
            return Intent().apply {
                putExtra(PAGE, page)
            }
        }


        //        For execute one process
//        fun newBundle(page: Int): PersistableBundle {
//            return PersistableBundle().apply {
//                putInt(PAGE, page)
//            }
//        }
    }
}