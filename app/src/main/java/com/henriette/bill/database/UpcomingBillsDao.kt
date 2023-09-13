package com.henriette.bill.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.henriette.bill.model.UpcomingBill

@Dao
interface UpcomingBillsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingBill(upComingBill: UpcomingBill)

    @Query("SELECT*FROM UpcomingBills WHERE billId= billId AND dueDate BETWEEN :startDate AND :endDate")
    fun queryExistingBill(billId: String, startDate: String, endDate: String):List<UpcomingBill>

    @Query("SELECT * FROM UpcomingBills WHERE frequency = :freq AND paid =:paid")
    fun getUpcomingBillsByFrequency(freq:String, paid:Boolean,):LiveData<List<UpcomingBill>>
}