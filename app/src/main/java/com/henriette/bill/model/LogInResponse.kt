package com.henriette.bill.model

import com.google.gson.annotations.SerializedName

data class LogInResponse(
    var message: String,
     @SerializedName("access_token") var accessToken:String,
    @SerializedName("user_id")  var userId: String
)
