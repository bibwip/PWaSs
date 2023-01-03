package com.thethreewisemen.pwass

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.thethreewisemen.pwass.adapters.CommentsAdapter
import com.thethreewisemen.pwass.firestore.getComments
import com.thethreewisemen.pwass.objects.Comment
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import com.thethreewisemen.pwass.firestore.getComments
import com.thethreewisemen.pwass.firestore.uploadComment
import com.thethreewisemen.pwass.objects.CommentSection
import kotlinx.coroutines.delay

class TestCommentsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test_comments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val section = CommentSection("test2", arrayListOf())


//        uploadComment(Comment(null, "", "", arrayListOf(), arrayListOf(
//            Comment(null, "", "", arrayListOf(), arrayListOf(), "nou nou", "Floris", 0)
//        ), "Goed bezig gast!", "Sybre", 3), section, null)
//        uploadComment(Comment(null, "", "", arrayListOf(), arrayListOf(), "NICE ONE!", "Jule", 3), section, null)

        val rec = view.findViewById<RecyclerView>(R.id.testComRecycler)
        rec.layoutManager = LinearLayoutManager(view.context)
        val adapter = CommentsAdapter(view.context, arrayListOf())
        rec.adapter = adapter

        getComments(section, adapter)

        lateinit var clack : OnClickListener

        val click = OnClickListener {
            view.findViewById<Button>(R.id.testButton).text = "me gusta comer caca culo pisny todo jajajajajajajajajajajajajajajaajj"
            for (com in section.comments) {
                if (com.userName == "Sybre") {
                    Log.d("FIRE", com.child[0].child[1].toString())
                    uploadComment(
                        Comment(
                            null,
                            "",
                            "",
                            arrayListOf(),
                            arrayListOf(),
                            "CHACA CHACA",
                            "Lem",
                            69
                        ),
                        section,
                        com.child[0].child[1]
                    )
                }
            }

            view.findViewById<Button>(R.id.testButton).setOnClickListener(clack)

        }

        clack = OnClickListener {
            view.findViewById<Button>(R.id.testButton).text = "piet"
            getComments(section, adapter)
            view.findViewById<Button>(R.id.testButton).setOnClickListener(click)
        }

        view.findViewById<Button>(R.id.testButton).setOnClickListener (click)
    }
}










































