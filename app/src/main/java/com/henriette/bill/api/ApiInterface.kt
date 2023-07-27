package com.henriette.bill.api

import com.henriette.bill.model.LogInRequest
import com.henriette.bill.model.LogInResponse
import com.henriette.bill.model.RegisterRequest
import com.henriette.bill.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiInterface {
    @POST("/users/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest)
    :Response<RegisterResponse>

    @POST("/users/login")
    suspend fun loginUser(@Body logInRequest: LogInRequest):Response<LogInResponse>

}