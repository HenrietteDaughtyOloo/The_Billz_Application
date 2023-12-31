package com.henriette.bill.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.henriette.bill.databinding.ActivityLogInBinding
import com.henriette.bill.model.LoginRequest
import com.henriette.bill.model.LoginResponse
import com.henriette.bill.utils.Constants
import com.henriette.bill.viewmodel.BillsViewModel
import com.henriette.bill.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    val userViewModel: UserViewModel by viewModels()
    val billsViewModel:BillsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setContentView(binding.root)
        binding.btnbutton2.setOnClickListener {
            clearErrors()
            validateLogin()
        }

        userViewModel.errLiveData.observe(this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            binding.pblogin.visibility = View.GONE
        })
        userViewModel.loginLiveData.observe(this, Observer { logResponse ->
            persistLogin(logResponse)
            billsViewModel.fetchRemoteBills()
            binding.pblogin.visibility = View.GONE
            Toast.makeText(this, logResponse.message, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        })
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun validateLogin() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        var error = false


        if (email.isBlank()) {
            binding.tilEmail.error = "Email is required"
            error = true
        }

        if (password.isBlank()) {
            binding.tilPassword.error = "password is required"
            error = true
        }

        if (!error) {
            val loginRequest = LoginRequest(
                email = email,
                password = password,
            )
            userViewModel.loginUser(loginRequest)
            binding.pblogin.visibility = View.VISIBLE
        }
    }

    fun persistLogin(logResponse: LoginResponse) {
        val sharedPrefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString(Constants.USER_ID, logResponse.userId)
        editor.putString(Constants.ACCESS_TOKEN, logResponse.accessToken)
        editor.apply()
    }

    fun clearErrors() {
//        binding.tilUsername.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null
    }
}
