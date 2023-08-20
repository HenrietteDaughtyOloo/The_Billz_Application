package com.henriette.bill.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Bills")
data class Bill(
    @PrimaryKey
    var name: String,
    var amount: Double,
    var frequency: String,
    var dueDate: String,

)
