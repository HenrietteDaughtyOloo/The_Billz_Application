package com.henriette.bill.repository

import com.henriette.bill.database.BillDb
import com.henriette.bill.model.Bill
import com.henriette.bill.BillApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BillsRepository {
    val db = BillDb.getDatabase(BillApp.appContext)
    val billDao = db.billDao()

    suspend fun saveBill(bill:Bill){
        withContext(Dispatchers.IO){
            billDao.insertBill(bill)
        }
    }
}