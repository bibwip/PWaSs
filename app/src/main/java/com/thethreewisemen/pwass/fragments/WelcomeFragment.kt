package com.thethreewisemen.pwass.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import com.thethreewisemen.pwass.R
import androidx.navigation.findNavController


class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.testButton).setOnClickListener {
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToVolgendeFrag()
            view.findNavController().navigate(action)
        }
    }
}