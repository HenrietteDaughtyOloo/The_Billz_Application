package com.henriette.bill.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.henriette.bill.model.Bill
@Dao
interface BillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBill(bill: Bill)

    @Query("SELECT * FROM Bills")
    fun getAllBills():LiveData<List<Bill>>

    @Query("SELECT *FROM Bills WHERE frequency = :freq")
    fun getReccurringBills(freq:String): List<Bill>
}