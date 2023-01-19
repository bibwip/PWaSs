package com.thethreewisemen.pwass.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.thethreewisemen.pwass.MainActivity
import com.thethreewisemen.pwass.R


class VolgendeFrag : Fragment(R.layout.fragment_volgende) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = requireActivity().getSharedPreferences(MainActivity.PREFS_NAME, AppCompatActivity.MODE_PRIVATE)

        view.findViewById<Button>(R.id.usernameFragbtn).setOnClickListener {
            prefs.edit().putString(MainActivity.USERNAME, view.findViewById<EditText>(R.id.usernameFrag).text.toString()).apply()
            val action = VolgendeFragDirections.actionVolgendeFragToMainFragment()
            view.findNavController().navigate(action)
        }
    }
}