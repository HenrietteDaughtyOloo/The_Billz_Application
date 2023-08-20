package com.henriette.bill.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henriette.bill.model.LogInRequest
import com.henriette.bill.model.LogInResponse
import com.henriette.bill.repository.LogInRepo
import kotlinx.coroutines.launch

class LogInViewModel:ViewModel() {
    val loginRepo = LogInRepo()
    var logInLiveData = MutableLiveData<LogInResponse>()
    var errLiveData = MutableLiveData<String>()



    fun logInUser(loginRequest: LogInRequest){
        viewModelScope.launch {
            val response = loginRepo.logInUser(loginRequest)

            if (response.isSuccessful){
                logInLiveData.postValue(response.body())
            }
            else{
                errLiveData.postValue(response.errorBody()?.string())
            }
        }

    }
}