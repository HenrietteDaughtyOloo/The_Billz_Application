package com.henriette.bill.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henriette.bill.model.RegisterRequest
import com.henriette.bill.model.RegisterResponse
import com.henriette.bill.repository.UserRepo
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    val userRepo = UserRepo()
    val regLiveData = MutableLiveData<RegisterResponse>()
    val errLiveData = MutableLiveData<String>()

    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response = userRepo.registerUser(registerRequest)

            if (response.isSuccessful){
                regLiveData.postValue(response.body())
        }
            else{
                errLiveData.postValue(response.errorBody()?.string())
            }
        }

    }

}