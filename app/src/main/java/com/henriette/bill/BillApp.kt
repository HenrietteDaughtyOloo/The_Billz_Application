package com.henriette.bill

import android.content.Context
import android.app.Application

class BillApp:Application() {
    companion object{
        lateinit var appContext: Context


    }

    override fun onCreate() {
        super.onCreate()
        appContext=applicationContext
    }

}





