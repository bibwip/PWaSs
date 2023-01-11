package com.thethreewisemen.pwass.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.adapters.CommentsAdapter
import com.thethreewisemen.pwass.firestore.getComments
import com.thethreewisemen.pwass.firestore.uploadComment
import com.thethreewisemen.pwass.objects.Comment
import com.thethreewisemen.pwass.objects.CommentSection
import com.thethreewisemen.pwass.objects.Post


class PostFragment : Fragment(R.layout.fragment_post) {

    val args : PostFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = view.findViewById<TextView>(R.id.postTitleTv)
        val userName = view.findViewById<TextView>(R.id.postUsernameTv)
        val bes = view.findViewById<TextView>(R.id.postBeschrijvingTv)
        val comRec = view.findViewById<RecyclerView>(R.id.postComRec)
        val comTxt = view.findViewById<EditText>(R.id.postComEt)
        val comBtn = view.findViewById<Button>(R.id.postComButton)


        val commentSection = CommentSection(args.commentSectionId, arrayListOf())

        title.text = args.postTitle
        userName.text = args.postUsername
        bes.text = args.postBes

        val adapter = CommentsAdapter(requireContext(), arrayListOf())
        comRec.layoutManager = LinearLayoutManager(requireContext())
        comRec.adapter = adapter
        getComments(commentSection, adapter)

        comBtn.setOnClickListener {
            val com = Comment(comTxt.text.toString(), "bobbie", commentSection.id)
            uploadComment(com, commentSection, null)
        }

    }
}