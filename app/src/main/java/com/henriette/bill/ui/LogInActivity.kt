package com.henriette.bill.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.henriette.bill.databinding.ActivityLogInBinding
import com.henriette.bill.model.LogInRequest
import com.henriette.bill.model.LogInResponse
import com.henriette.bill.utils.Constants
import com.henriette.bill.viewmodel.LogInViewModel

class LogInActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    val userViewModel: LogInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.tvDonthaveaccount.setOnClickListener {

        }
        binding.btnLogIn.setOnClickListener {
            validateLogIn()
        }
        userViewModel.logInLiveData.observe(this, Observer { loginResponse ->
            persistLogIn(loginResponse)

            binding.pbLogIn.visibility = View.GONE
            Toast.makeText(this, loginResponse.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        })
        userViewModel.errLiveData.observe(this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()
            binding.pbLogIn.visibility = View.GONE
        })

    }

    private fun validateLogIn() {
        val email = binding.etEmail.text.toString()
        val password = binding.etLogInPassword.text.toString()
        var error = false

        if (email.isBlank()) {
            binding.tilEmail.error = "Email  is required"
            error = true

        }

        if (password.isBlank()) {
            binding.tilLogInPassword.error = " Password is required"
            error = true

        }
        if (!error) {
            val loginRequest = LogInRequest(
                email = email,
                password = password

            )
            binding.pbLogIn.visibility = View.VISIBLE
            userViewModel.logInUser(loginRequest)
        }


    }

    fun persistLogIn(logInResponse: LogInResponse){
        val sharedPrefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString(Constants.USER_ID, logInResponse.userId)
        editor.putString(Constants.ACCESS_TOKEN, logInResponse.accessToken)
        editor.apply()

    }
}
