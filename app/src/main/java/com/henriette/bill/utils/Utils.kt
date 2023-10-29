package com.henriette.bill.utils

import android.icu.util.CurrencyAmount
import android.view.View
import java.text.DecimalFormat

class Utils {
    companion object{
        fun formatCurrency(amount:Double):String{
            val fmt = DecimalFormat("KES #,###")

            return fmt.format(amount)
        }
    }
    fun View.show() {
        this.visibility = View.VISIBLE
    }

    fun View.hide() {
        this.visibility = View.GONE
    }
}