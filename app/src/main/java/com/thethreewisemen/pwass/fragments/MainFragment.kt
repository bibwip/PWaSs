package com.thethreewisemen.pwass.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.adapters.RecyclerAdapter
import com.thethreewisemen.pwass.firestore.getPosts
import com.thethreewisemen.pwass.objects.Post

class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = RecyclerAdapter(arrayListOf())
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerPosts)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter
        getPosts(adapter)




    }
}