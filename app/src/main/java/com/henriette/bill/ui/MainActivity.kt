package com.henriette.bill.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.henriette.bill.databinding.ActivityMainBinding
import com.henriette.bill.utils.Constants


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        redirectUser()

        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent(this@MainActivity, SignUpActivity::class.java)
            startActivity(intent)
        }, 3000)

    }

    fun redirectUser() {
        val sharedPrefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val userId = sharedPrefs.getString(Constants.USER_ID, Constants.EMPTY_STRING)

        if (userId.isNullOrBlank()) {
            startActivity(Intent(this, LogInActivity::class.java))
        } else {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        finish()
    }


}



