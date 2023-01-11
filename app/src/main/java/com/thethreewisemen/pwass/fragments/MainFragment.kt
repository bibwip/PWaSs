package com.thethreewisemen.pwass.fragments


import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.thethreewisemen.pwass.MainActivity
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.adapters.RecyclerAdapter
import com.thethreewisemen.pwass.firestore.getPosts


class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = RecyclerAdapter(arrayListOf(), (activity as MainActivity))
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerPosts)
        val button = view.findViewById<FloatingActionButton>(R.id.mainAddPostBtn)
        val refresh = view.findViewById<Button>(R.id.mainRefreshBtn)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter
        getPosts(adapter)

        button.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToAddPostFragment()
            view.findNavController().navigate(action)
        }
        refresh.setOnClickListener {
            getPosts(adapter)
        }

        val main = (activity as MainActivity)

        if (main.hasCustomTheme) {
            refresh.background.setTint(Color.parseColor(main.colorPrimary))
            //button.background.setTint(Color.parseColor(main.colorPrimary))

        }





    }
}