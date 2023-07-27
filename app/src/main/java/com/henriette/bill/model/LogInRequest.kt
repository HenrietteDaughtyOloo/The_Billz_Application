package com.henriette.bill.model

import com.google.gson.annotations.SerializedName

data class LogInRequest(
    @SerializedName("email") var email: String,
    @SerializedName("passwors") var password: String,
)
