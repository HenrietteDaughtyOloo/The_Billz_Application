package com.henriette.bill.repository

import com.henriette.bill.api.ApiClient
import com.henriette.bill.api.ApiInterface
import com.henriette.bill.model.LogInRequest
import com.henriette.bill.model.LogInResponse
import com.henriette.bill.model.RegisterRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LogInRepo {
    val apiClient = ApiClient.buildClient(ApiInterface::class.java)

    suspend fun logInUser(logInRequest:LogInRequest): Response<LogInResponse>{
        return withContext(Dispatchers.IO){
            apiClient.loginUser(logInRequest)
        }
    }
}