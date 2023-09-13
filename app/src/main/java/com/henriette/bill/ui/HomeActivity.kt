package com.henriette.bill.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.henriette.bill.R
import com.henriette.bill.databinding.ActivityHomeBinding
import com.henriette.bill.viewmodel.BillsViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var binding:ActivityHomeBinding
    val billsViewModel: BillsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        billsViewModel.createRecurringBills()
    }

    override fun onResume() {
        super.onResume()
        setupBottomNav()
    }
    fun setupBottomNav(){
        binding.bnvHome.setOnItemSelectedListener{ menuItem->
            when(menuItem.itemId){
            R.id.summary->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fcvHome, SummaryFragment()).commit()
                true

            }
                R.id.upcoming->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fcvHome, UpcomingBillsFragment())
                            .commit()
                        true

                }
                        R.id.paid->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fcvHome, PaidFragment())
                            .commit()
                            true
                    }


                R.id.setting->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fcvHome, SettingsFragment())
                            .commit()
                    true
                    }
                        else->false

                    }

                }
            }



}