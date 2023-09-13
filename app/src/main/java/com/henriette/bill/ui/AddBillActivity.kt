package com.henriette.bill.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.henriette.bill.R
import com.henriette.bill.databinding.ActivityAddBillBinding
import com.henriette.bill.model.Bill
import com.henriette.bill.utils.Constants
import com.henriette.bill.viewmodel.BillsViewModel
import java.util.Calendar
import java.util.UUID

class AddBillActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddBillBinding
    private val billsViewModel: BillsViewModel by viewModels()
    var selectedDate = 0
    var selectedMonth = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBillBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setupFreqSpinner()
        binding.btnSaveBill.setOnClickListener {
            validateBill()
        }
    }

    private fun setupFreqSpinner() {
        val frequencies = arrayOf(Constants.WEEKLY, Constants.MONTHLY, Constants.ANNUALLY)
        val frequencyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, frequencies)
        frequencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spFrequency.adapter = frequencyAdapter

        binding.spFrequency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (binding.spFrequency.selectedItem.toString()) {
                    Constants.WEEKLY -> {
                        showSpinner()
                        setupDueDateSpinner(Array(7) { it + 1 })
                    }

                    Constants.MONTHLY -> {
                        showSpinner()
                        setupDueDateSpinner(Array(31) { it + 1 })
                    }

                    Constants.ANNUALLY -> {
                        showDatePicker()
                        setUpDpDueDate()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    fun showSpinner() {
        binding.dpDueDate.show()
        binding.spDueDate.hide()
    }

    fun showDatePicker() {
        binding.dpDueDate.show()
        binding.spDueDate.hide()
    }

    fun setupDueDateSpinner(dates: Array<Int>) {
        val dueDateAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, dates)
        dueDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spDueDate.adapter = dueDateAdapter
    }

    fun setUpDpDueDate() {
        val cal = Calendar.getInstance()
        binding.dpDueDate.init(
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)

        ) { _, _, month, date ->
            selectedDate = date
            selectedMonth = month + 1
        }
    }

    fun validateBill() {
        val name = binding.etName.text.toString()
        val amount = binding.etAmount.text.toString()
        val frequency = binding.spFrequency.selectedItem.toString()
        var dueDate = if (frequency == Constants.ANNUALLY) {
            "$selectedDate/$selectedMonth"
        } else {
            binding.spDueDate.selectedItem.toString()
        }
        var error = false
        if (name.isBlank()) {
            error = true
            binding.etName.error = getString(R.string.last_name)
        }
        if (amount.isBlank()) {
            error = true
            binding.etName.error = getString(R.string.paid)
        }

        if (!error) {
            val prefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
            val userId = prefs.getString(Constants.USER_ID, Constants.EMPTY_STRING)
            val newBill = Bill(
                name = name,
                amount = amount.toDouble(),
                frequency = frequency,
                dueDate = dueDate,
                billId = UUID.randomUUID().toString(),
                userId = userId.toString()
            )
            billsViewModel.saveBill(newBill)
            Toast.makeText(this, ("$name Bill has been Successfully Saved"), Toast.LENGTH_SHORT)
                .show()
            finish()
            clearForm()

        }
    }

    fun clearForm() {
        binding.etName.setText(Constants.EMPTY_STRING)
        binding.etAmount.setText(Constants.EMPTY_STRING)
        binding.spFrequency.setSelection(0)
        showSpinner()
        binding.spDueDate.setSelection(0)
    }
}

    fun View.show() {
        this.visibility = View.VISIBLE
    }

    fun View.hide() {
        this.visibility = View.GONE
    }
