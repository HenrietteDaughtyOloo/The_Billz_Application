package com.henriette.bill.model

data class LogInResponse(
    var message: String,
    var accessToken:String,
    var userId: String
)
