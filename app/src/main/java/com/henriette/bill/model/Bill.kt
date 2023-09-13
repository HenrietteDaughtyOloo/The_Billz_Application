package com.henriette.bill.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Bills",
    indices=[Index(value=["billId","dueDate"],unique=true)])data class Bill(
    @PrimaryKey var billId:String,
    var name: String,
    var amount: Double,
    var frequency: String,
    var dueDate: String,
    var userId: String,

)
