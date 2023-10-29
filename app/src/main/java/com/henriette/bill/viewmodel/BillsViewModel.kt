package com.henriette.bill.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henriette.bill.database.UpcomingBillsDao
import com.henriette.bill.model.Bill
import com.henriette.bill.model.BillsSummary
import com.henriette.bill.model.UpcomingBill
import com.henriette.bill.repository.BillsRepository
import kotlinx.coroutines.launch

class BillsViewModel:ViewModel() {
    private val billsRepo= BillsRepository()
    val summaryLiveData= MutableLiveData<BillsSummary>()

    fun saveBill(bill: Bill){
        viewModelScope.launch {
            billsRepo.saveBill(bill)
        }
    }
    fun insertUpcomingBill(upcomingBill: UpcomingBill){
        viewModelScope.launch {
            billsRepo.insertUpcomingBill(upcomingBill)
        }
    }
//    fun getAllBills(): LiveData<List<Bill>> {
//        return billsRepo.getAllBills()
//    }

    fun getUpcomingBillsByFrequency(freq:String):LiveData<List<UpcomingBill>>{
        return billsRepo.getUpcomingBillsByFrequency(freq)
    }

    fun updateUpcomingBill(upcomingBill: UpcomingBill){
        viewModelScope.launch {
            billsRepo.updateUpcomingBill(upcomingBill)
        }
    }

    fun getPaidBills():LiveData<List<UpcomingBill>>{
        return billsRepo.getPaidBills()
    }

    fun fetchRemoteBills(){
        viewModelScope.launch {
            billsRepo.fetchRemoteBills()
            billsRepo.fetchRemoteUpcomingBills()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMonthlySummary(){
        viewModelScope.launch {
            summaryLiveData.postValue(billsRepo.getMonthlySummary().value)
        }
    }


}