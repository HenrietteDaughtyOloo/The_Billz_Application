package com.henriette.bill.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.henriette.bill.R
import com.henriette.bill.databinding.FragmentUpcomingBillsBinding
import com.henriette.bill.viewmodel.BillsViewModel


class UpComingBillsFragment : Fragment() {
    private  val binding : FragmentUpcomingBillsBinding?=null
    val billsViewModel = BillsViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentUpcomingBillsBinding.inflate(inflater,container, "false")
        return binding.root

        return inflater.inflate(R.layout.fragment_upcoming_bills, container, false)

    }

    override fun onResume() {
        super.onResume()
        getUpComingBills()
    }
    fun getUpComingBills(){
        billsViewModel.getUpComingBillsByFrequency(com.henriette.bill.utils.Constants.WEEKLY)
            .observe(this){weeklyBills->
                val adapter = UpcomingBillsAdapter(weeklyBills)
                binding?.rvWeekly?.LayoutManager= LinearLayoutManager(requireContext())
                binding?.rvWeekly?.adapter=adapter

            }
        billsViewModel.getUpComingBillsByFrequency(com.henriette.bill.utils.Constants.WEEKLY)
            .observe(this){weeklyBills->
                val adapter = UpcomingBillsAdapter(weeklyBills)
                binding?.rvWeekly?.LayoutManager= LinearLayoutManager(requireContext())
                binding?.rvWeekly?.adapter=adapter

            }
        billsViewModel.getUpComingBillsByFrequency(com.henriette.bill.utils.Constants.WEEKLY)
            .observe(this){weeklyBills->
                val adapter = UpcomingBillsAdapter(weeklyBills)
                binding?.rvWeekly?.LayoutManager= LinearLayoutManager(requireContext())
                binding?.rvWeekly?.adapter=adapter

            }



    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null

    }



}