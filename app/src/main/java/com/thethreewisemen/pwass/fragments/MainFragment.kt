package com.thethreewisemen.pwass.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.adapters.RecyclerAdapter
import com.thethreewisemen.pwass.objects.Post

class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postList = arrayListOf(
            Post("test", "deze autist heeft hulp nodig", "HELP! ik krijg een autistische aanval", "autist420"),
            Post("test dos", "deze autist heeft hulp nodig", "HELP! ik krijg een autistische aanval", "autist420"),
            Post("test tre", "deze autist heeft hulp nodig", "HELP! ik krijg een autistische aanval", "autist420"),
            Post("test 4", "deze autist heeft hulp nodig", "HELP! ik krijg een autistische aanval", "autist420"),
            Post("test V", "deze autist heeft hulp nodig", "HELP! ik krijg een autistische aanval", "autist420"),
            Post("test seis", "deze autist heeft hulp nodig", "HELP! ik krijg een autistische aanval", "autist420"),
            Post("test kleven", "deze autist heeft hulp nodig", "HELP! ik krijg een autistische aanval", "autist420"),
            Post("test gat", "deze autist heeft hulp nodig", "HELP! ik krijg een autistische aanval", "autist420")
        )

        val adapter = RecyclerAdapter(postList)
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerPosts)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter




    }
}