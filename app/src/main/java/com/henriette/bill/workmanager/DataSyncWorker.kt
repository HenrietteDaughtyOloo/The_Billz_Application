package com.henriette.bill.workmanager

import android.content.Context
import android.content.SyncContext
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.henriette.bill.repository.BillsRepository

class DataSyncWorker(context: Context,workerParams: WorkerParameters) :
    CoroutineWorker(context,workerParams){
    val billsRepo = BillsRepository()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        billsRepo.synchedBills()
        billsRepo.synchedUpcomingBills()

        return Result.success()
    }
}