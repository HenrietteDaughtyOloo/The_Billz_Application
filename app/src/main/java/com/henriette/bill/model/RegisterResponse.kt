package com.henriette.bill.model

import com.google.gson.annotations.Expose


data class RegisterResponse(
    @Expose var message:String,
    @Expose var user: User
)
