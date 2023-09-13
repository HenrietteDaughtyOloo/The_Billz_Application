package com.henriette.bill.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henriette.bill.database.UpcomingBillsDao
import com.henriette.bill.model.Bill
import com.henriette.bill.model.UpcomingBill
import com.henriette.bill.repository.BillsRepository
import kotlinx.coroutines.launch

class BillsViewModel:ViewModel() {
    private val billsRepo = BillsRepository()
    private val saveBillResult = MutableLiveData<Result<Unit>>()


    fun  saveBill(bill: Bill){
        viewModelScope.launch {
            try {
                billsRepo.saveBill(bill)
                saveBillResult.postValue(Result.success(Unit))
            }catch (e: Exception){
                saveBillResult.postValue(Result.failure(e))
            }
//            billsRepo.saveBill(bill)
        }
    }
    fun createRecurringBills(){
        viewModelScope.launch {
            billsRepo.createRecurringMonthlyBills()
            billsRepo.createRecurringMonthlyBills()
        }
    }
    fun getUpcomingBillsByFrequency(freq:String): LiveData<List<UpcomingBillsDao>> {
        return billsRepo.getUpcomingBillsByFrequency(freq)

    }
}