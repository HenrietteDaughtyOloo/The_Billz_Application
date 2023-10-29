package com.henriette.bill.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henriette.bill.model.LoginRequest
import com.henriette.bill.model.LoginResponse
import com.henriette.bill.model.RegisterRequest
import com.henriette.bill.model.RegisterResponse
import com.henriette.bill.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    val userRepo=UserRepository()
    val regLiveData= MutableLiveData<RegisterResponse>()
    val errLiveData=MutableLiveData<String>()
    val loginLiveData=MutableLiveData<LoginResponse>()


    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response= userRepo.registerUser(registerRequest)

            if (response.isSuccessful){
                regLiveData.postValue(response.body())
            }
            else{
                errLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response= userRepo.loginUser(loginRequest)
            if (response.isSuccessful){
                loginLiveData.postValue(response.body())
            }
            else{
                errLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}