package com.henriette.bill.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.henriette.bill.databinding.ActivityUpcomingBillsListBinding
import com.henriette.bill.model.UpcomingBill
import java.text.FieldPosition

class UpcomingBillsAdapter (var upcomingBills :List<UpcomingBill>):Adapter<UpcomingBillsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingBillsViewHolder {
        val binding = ActivityUpcomingBillsListBinding.inflate(LayoutInflater.from(parent.context))
        return UpcomingBillsViewHolder(binding)


    }

    override fun getItemCount(): Int {
        return upcomingBills.size

    }

    override fun onBindViewHolder(holder: UpcomingBillsViewHolder, position: Int) {
        val upComingBill =upcomingBills.get(position)
        holder.binding.apply {
            cbUpcoming.text=upComingBill.name
            tvAmount.text= upComingBill.amount.toString()
        }
    }
}

class UpcomingBillsViewHolder(var binding:ActivityUpcomingBillsListBinding):ViewHolder(binding.root)