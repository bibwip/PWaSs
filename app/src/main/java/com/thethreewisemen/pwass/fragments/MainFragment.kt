package com.thethreewisemen.pwass.fragments


import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.thethreewisemen.pwass.MainActivity
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.adapters.RecyclerAdapter
import com.thethreewisemen.pwass.helpers.getPosts
import com.thethreewisemen.pwass.helpers.refreshPosts
import com.thethreewisemen.pwass.objects.Post
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val main = (activity as MainActivity)
        val button = view.findViewById<FloatingActionButton>(R.id.mainAddPostBtn)
        val refresher = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshPost)

        val adapter = RecyclerAdapter(arrayListOf(), main,
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

        refresher.setOnRefreshListener {
            refreshPosts(adapter, refresher)
            refresher.isRefreshing = true
        }

        button.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToAddPostFragment()
            view.findNavController().navigate(action)
        }

        if (main.hasCustomTheme) {
            mainAddPostBtn.backgroundTintList = ColorStateList.valueOf(Color.parseColor(main.colorPrimary));
        }




    }
}