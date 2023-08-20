package com.henriette.bill.repository

import com.henriette.bill.api.ApiClient
import com.henriette.bill.api.ApiInterface
import com.henriette.bill.model.RegisterRequest
import com.henriette.bill.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepo {
    val apiClient = ApiClient.buildClient(ApiInterface::class.java)
    suspend fun registerUser (registerRequest: RegisterRequest):Response<RegisterResponse>{
        return withContext(Dispatchers.IO){
            apiClient.registerUser(registerRequest)
        }
    }
}