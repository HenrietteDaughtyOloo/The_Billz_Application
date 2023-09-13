package com.henriette.bill.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.henriette.bill.databinding.ActivityAddBillBinding
import com.henriette.bill.viewmodel.BillsViewModel

class AddBillsAdapter: AppCompatActivity() {
    lateinit var binding: ActivityAddBillBinding
    val billsViewModel: BillsViewModel by viewModels()
    lateinit var billsAdapter: AddBillActivity

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }
}