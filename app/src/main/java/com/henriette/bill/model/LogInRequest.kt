package com.henriette.bill.model

import com.google.gson.annotations.SerializedName

data class LogInRequest(
     var email: String,
    var password: String,
)
