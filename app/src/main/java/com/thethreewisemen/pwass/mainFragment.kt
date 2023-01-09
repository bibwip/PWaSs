package com.thethreewisemen.pwass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thethreewisemen.pwass.objects.Post
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.post_view.*

class mainFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var postList = mutableListOf(
            Post("test", "deze autist heeft hulp nodig", "HELP! ik krijg een autistische aanval", "autist420"),
            Post("test dos", "deze autist heeft hulp nodig", "HELP! ik krijg een autistische aanval", "autist420"),
            Post("test tre", "deze autist heeft hulp nodig", "HELP! ik krijg een autistische aanval", "autist420"),
            Post("test 4", "deze autist heeft hulp nodig", "HELP! ik krijg een autistische aanval", "autist420"),
            Post("test V", "deze autist heeft hulp nodig", "HELP! ik krijg een autistische aanval", "autist420"),
            Post("test seis", "deze autist heeft hulp nodig", "HELP! ik krijg een autistische aanval", "autist420"),
            Post("test kleven", "deze autist heeft hulp nodig", "HELP! ik krijg een autistische aanval", "autist420"),
            Post("test gat", "deze autist heeft hulp nodig", "HELP! ik krijg een autistische aanval", "autist420"),

        )

        val adapter = RecyclerAdapter(postList)



        val recycler = view.findViewById<RecyclerView>(R.id.postViews)

        recycler.layoutManager = LinearLayoutManager(context)

        recycler.adapter = adapter




    }
}