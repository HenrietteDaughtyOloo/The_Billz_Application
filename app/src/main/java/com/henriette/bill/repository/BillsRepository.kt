package com.henriette.bill.repository

import androidx.lifecycle.LiveData
import com.henriette.bill.BillApp
import com.henriette.bill.database.BillDb
import com.henriette.bill.database.UpcomingBillsDao
import com.henriette.bill.model.Bill
import com.henriette.bill.model.UpcomingBill
import com.henriette.bill.utils.DateTimeUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class BillsRepository {
  private val db = BillDb.getDatabase(BillApp.appContext)
  private val billDao = db.billDao()
    private val upComingBillsDao = db.upComingBillsDao()

    suspend fun saveBill(bill:Bill){
        withContext(Dispatchers.IO){
            billDao.saveBill(bill)
        }
    }
    fun getAllBills():LiveData<List<Bill>>{
    return billDao.getAllBills()
    }

    suspend fun insertUpcomingBill(upComingBill: UpcomingBill){
        withContext(Dispatchers.IO){
            upComingBillsDao.insertUpcomingBill(upComingBill)
        }
    }
    suspend fun createRecurringMonthlyBills(){
        withContext(Dispatchers.IO){
            val monthlyBills = billDao.getReccurringBills(com.henriette.bill.utils.Constants.MONTHLY)
            val startDate = DateTimeUtils.getFirstDayOfMonth()
            val  endDate = DateTimeUtils.getLastDayOfMonth()
            val month = DateTimeUtils.getCurrentMonth()
            val year = DateTimeUtils.getCurrentYear()
            monthlyBills.forEach{bill->
            val  existing = upComingBillsDao.queryExistingBill(bill.billId, startDate, endDate)
            if (existing.isEmpty()){
             val newUpcomingBill= UpcomingBill(
                 upcomingBillId = UUID.randomUUID().toString(),
                 billId = bill.billId,
                 name = bill.name,
                 amount = bill.amount,
                 frequency = bill.frequency,
                 dueDate = "${bill.dueDate}/${month}/${year}",
                 userId = bill.userId,
                 paid = false
             )
                upComingBillsDao.insertUpcomingBill(newUpcomingBill)
            }
        }
    }
}

    suspend fun createRecurringWeeklyBills(){
        withContext(Dispatchers.IO){
            val weeklyBills = billDao.getReccurringBills(com.henriette.bill.utils.Constants.WEEKLY)
            val startDate = DateTimeUtils.getFirstDateOfWeek()
            val endDate = DateTimeUtils.getLastDateOfWeek()
            weeklyBills.forEach{ bill->
                val existing = upComingBillsDao.queryExistingBill(bill.billId, startDate, endDate)
                if (existing.isEmpty()){
                    val weeklyBill = UpcomingBill(
                        upcomingBillId = UUID.randomUUID().toString(),
                        billId=bill.billId,
                        name = bill.name,
                        amount = bill.amount,
                        frequency = bill.frequency,
                        dueDate = DateTimeUtils.getDateOfWeekDay(bill.dueDate),
                        userId =bill.userId,
                        paid = false,
                    )
                    upComingBillsDao.insertUpcomingBill(weeklyBill)
                }
            }

    }
}
    fun getUpcomingBillsByFrequency():LiveData<List<UpcomingBillsDao>>{
        return upComingBillsDao.getUpcomingBillsByFrequency(freq, false)
    }
}