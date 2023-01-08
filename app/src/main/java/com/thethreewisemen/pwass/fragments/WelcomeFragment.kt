package com.thethreewisemen.pwass.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import com.thethreewisemen.pwass.R
import androidx.navigation.findNavController
import com.thethreewisemen.pwass.MainActivity


class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.testButton)
        val main = (activity as MainActivity)

        if (main.hasCustomTheme) {
            button.background.setTint(Color.parseColor(main.colorPrimary))
        }

        // Druk op de knop stuurt je door naar volgende bestemming
        button.setOnClickListener {
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToVolgendeFrag()
            view.findNavController().navigate(action)
        }
    }
}