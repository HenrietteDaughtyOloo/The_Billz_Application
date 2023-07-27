package com.henriette.bill.model
import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("first_name") var firstName: String,
    @SerializedName("phone_number")  var phoneNumber:String,
    @SerializedName("last_name")  var lastName: String,
    var email: String,
    val password:String,
)
