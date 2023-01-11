package com.thethreewisemen.pwass.fragments


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.thethreewisemen.pwass.MainActivity
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.adapters.RecyclerAdapter
import com.thethreewisemen.pwass.firestore.getPosts
import com.thethreewisemen.pwass.objects.Post


class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val main = (activity as MainActivity)
        val button = view.findViewById<FloatingActionButton>(R.id.mainAddPostBtn)
        val refresh = view.findViewById<Button>(R.id.mainRefreshBtn)

        val adapter = RecyclerAdapter(arrayListOf(), (activity as MainActivity),
            object : RecyclerAdapter.OnItemClickListener {
                override fun onItemClick(item: Post?, position: Int) {
                    if (item != null) {
                        val action = MainFragmentDirections.actionMainFragmentToPostFragment(
                            item.naam_poster,
                            item.titel,
                            item.beschrijving,
                            item.commentSection.id
                        )
                        findNavController().navigate(action)
                    }


                }
            })
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerPosts)
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


        if (main.hasCustomTheme) {
            refresh.background.setTint(Color.parseColor(main.colorPrimary))
            //button.background.setTint(Color.parseColor(main.colorPrimary))

        }





    }
}