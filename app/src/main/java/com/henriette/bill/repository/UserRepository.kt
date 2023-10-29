package com.henriette.bill.repository

import com.henriette.bill.api.ApiClient
import com.henriette.bill.api.ApiInterface
import com.henriette.bill.model.LoginRequest
import com.henriette.bill.model.LoginResponse
import com.henriette.bill.model.RegisterRequest
import com.henriette.bill.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient=ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun registerUser(registerRequest: RegisterRequest):Response<RegisterResponse>{
        return withContext(Dispatchers.IO){
            apiClient.registerUser(registerRequest)
        }
    }

    suspend fun loginUser(loginRequest: LoginRequest):Response<LoginResponse> {
        return withContext(Dispatchers.IO){
            apiClient.loginUser(loginRequest)
        }
    }
}