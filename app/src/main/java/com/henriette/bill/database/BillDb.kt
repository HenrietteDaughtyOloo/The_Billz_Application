package com.henriette.bill.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.henriette.bill.model.Bill
import com.henriette.bill.model.UpcomingBill


@Database(entities = [Bill::class, UpcomingBill::class], version = 5)
abstract class BillDb:RoomDatabase() {
    abstract fun billDao():BillDao

    abstract fun upcomingBIllsDao():UpcomingBillsDao

    companion object{
        private var database:BillDb? = null
        fun getDatabase(context: Context):BillDb{
            if (database==null){
                database = Room
                    .databaseBuilder(context,BillDb::class.java,"BillDb")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database as BillDb
        }
    }
}
