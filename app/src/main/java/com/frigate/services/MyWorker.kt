package com.frigate.services

import android.content.Context
import android.util.Log
import androidx.work.*

class MyWorker(
    context: Context,
    private val workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        log("On doWork ")
        val page = workerParams.inputData.getInt(PAGE, 0)
        for (i in 0 until 5) {
            Thread.sleep(1000)
            log("Timer $i $page")
        }
        return Result.success()
    }

    private fun log(mes: String) {
        Log.d("Service", "My intent service: $mes")
    }

    companion object {
        private const val PAGE = "page"
        const val WORK_NAME = "work name"

        fun makeReq(page: Int): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<MyWorker>().apply {
                setInputData(workDataOf(PAGE to page))
                setConstraints(makeConstrains())
            }.build()
        }

        private fun makeConstrains(): Constraints {
            return Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED).build()
        }
    }
}