package com.henriette.bill.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.henriette.bill.databinding.ActivitySignUpBinding
import com.henriette.bill.model.RegisterRequest
import com.henriette.bill.viewmodel.UserViewModel

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    val userViewModel:UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }




    override fun onResume() {
        super.onResume()
        binding.tvLogInto?.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
             startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener{
            validateSignUp()
        }

        userViewModel.regLiveData.observe(this, Observer { regResponse->
            binding.progressBar2.visibility=View.GONE
            Toast.makeText(this, regResponse.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LogInActivity::class.java))

        })
        userViewModel.errLiveData.observe(this,Observer{ err->
            Toast.makeText(this,err,Toast.LENGTH_LONG).show()
            binding.progressBar2.visibility=View.GONE

        })




//        userViewModel.regLiveData.observe(this, Observer {
//                regResponse->
//            Toast.makeText(this, regResponse.message,Toast.LENGTH_LONG).show()
//            startActivity(Intent(this, LogInActivity::class.java))
//
//        })
//
//        userViewModel.regLiveData.observe(this, Observer {
//                error->
//            Toast.makeText(this, error.message,Toast.LENGTH_LONG).show()
//
//        })

    }

    private fun validateSignUp() {
        clearErrors()
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val email = binding.etEmail.text.toString()
        var error = false

        if (firstName.isBlank()) {
            binding.tilFirstName.error = "User Name is required to proceed"
            error = true
        }
        if (lastName.isBlank()) {
            binding.tilFirstName.error = "User Name is required to proceed"
            error = true
        }

        if (password.isBlank()) {
            binding.tilPassword.error = "Password needed to proceed"
            error = true
        }
        if (phoneNumber.isBlank()) {
            binding.tilPhoneNumber.error = "Enter your phone number to proceed"
            error = true
        }
        if (email.isBlank()) {
            binding.tilEmail.error = "Enter your email to proceed"
            error = true
        }
        if (confirmPassword.isBlank()){
            binding.tilConfirmPassword.error="Please confirm your password."
            error=true
        }
        if (confirmPassword != password){
            binding.tilConfirmPassword.error ="Passwords don't match"
            error=true
        }

        if (!error) {
//            if (password == confirmPassword) {
//                // Successful login
//                val intent = Intent(this, LogInActivity::class.java)
//                startActivity(intent)
//                finish()
//            } else {
//                // Login failed
//                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
//            }
            val registerRequest = RegisterRequest(
                firstName = firstName,
                lastName = lastName,
                email = email,
                password = password,
                phoneNumber = phoneNumber
            )

            userViewModel.registerUser(registerRequest)
        }
        binding.progressBar2.visibility=View.VISIBLE

    }


    private fun clearErrors() {
        binding.tilFirstName.error = null
        binding.tilPassword.error = null
    }
}

