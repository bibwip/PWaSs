package com.thethreewisemen.pwass.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.adapters.RecyclerAdapter
import com.thethreewisemen.pwass.firestore.getPosts
import com.thethreewisemen.pwass.objects.Post

class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = RecyclerAdapter(arrayListOf())
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




    }
}