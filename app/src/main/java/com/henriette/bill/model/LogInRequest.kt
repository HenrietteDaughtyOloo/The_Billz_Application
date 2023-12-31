package com.henriette.bill.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @Expose var email:String,
    @Expose var password:String,
)
