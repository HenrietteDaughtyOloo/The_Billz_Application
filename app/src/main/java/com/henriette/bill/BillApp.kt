package com.henriette.bill

import android.content.Context
import android.app.Application
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.henriette.bill.utils.Constants
import com.henriette.bill.workmanager.DataSyncWorker
import com.henriette.bill.workmanager.UpcomingBillsWorker
import java.util.concurrent.TimeUnit

class BillzApp:Application() {
    companion object{
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext=applicationContext

        val periodicWorkRequest = PeriodicWorkRequestBuilder<UpcomingBillsWorker>(15, TimeUnit.MINUTES)
            .addTag(Constants.CREATE_RECURRING_BILLS).build()

        WorkManager
            .getInstance(appContext)
            .enqueueUniquePeriodicWork(Constants.CREATE_RECURRING_BILLS, ExistingPeriodicWorkPolicy.KEEP,periodicWorkRequest)

        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val syncPeriodicWorkRequest = PeriodicWorkRequestBuilder<DataSyncWorker>(15,TimeUnit.MINUTES)
            .addTag(Constants.SYNC_BILLS).setConstraints(constraints).build()

        WorkManager.getInstance(appContext)
            .enqueueUniquePeriodicWork(Constants.SYNC_BILLS,ExistingPeriodicWorkPolicy.KEEP,syncPeriodicWorkRequest)
    }
}