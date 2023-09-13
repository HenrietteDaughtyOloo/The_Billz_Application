package com.henriette.bill.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.henriette.bill.R
import com.henriette.bill.utils.Constants


class SettingsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)


        val logOutButton = view.findViewById<Button>(R.id.btnLogOut)

        logOutButton.setOnClickListener {
            logOut()
        }
        return view

    }
    private fun logOut(){
        val sharedPrefs = requireActivity().getSharedPreferences(Constants.PREFS,Context.MODE_PRIVATE)

        val editor = sharedPrefs.edit()
        editor.clear().apply()

        val intent = Intent(requireContext(), LogInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        requireActivity().finish()
        Toast.makeText(requireContext(),"Successfully Logged out", Toast.LENGTH_SHORT).show()

    }


}