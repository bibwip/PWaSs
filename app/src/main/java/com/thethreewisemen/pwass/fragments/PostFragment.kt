package com.thethreewisemen.pwass.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.thethreewisemen.pwass.MainActivity
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.adapters.CommentsAdapter
import com.thethreewisemen.pwass.helpers.*
import com.thethreewisemen.pwass.objects.Comment
import com.thethreewisemen.pwass.objects.CommentSection


class PostFragment : Fragment(R.layout.fragment_post) {

    private val args : PostFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prefs = requireActivity().getSharedPreferences(MainActivity.PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
        val currentUser = prefs.getString(MainActivity.USERNAME, "")!!

        val title = view.findViewById<TextView>(R.id.postTitleTv)
        val userName = view.findViewById<TextView>(R.id.postUsernameTv)
        val bes = view.findViewById<TextView>(R.id.postBeschrijvingTv)
        val comRec = view.findViewById<RecyclerView>(R.id.postComRec)
        val comTxt = view.findViewById<EditText>(R.id.postComEt)
        val comBtn = view.findViewById<FloatingActionButton>(R.id.postComButton)
        val refresh = view.findViewById<SwipeRefreshLayout>(R.id.swipeRefreshComment)
        val comBigTxt = view.findViewById<EditText>(R.id.postComEtField)
        var tempParent : Comment? = null



        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true ) {
                override fun handleOnBackPressed() {
                    if (view.findViewById<FrameLayout>(R.id.postFragComField).visibility == View.VISIBLE) {
                        view.findViewById<FrameLayout>(R.id.postFragComField).visibility = View.GONE
                        view.findViewById<FrameLayout>(R.id.postFragMain).visibility = View.VISIBLE
                    }else {
                        val action = PostFragmentDirections.actionPostFragmentToMainFragment()
                        view.findNavController().navigate(action)
                    }
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        val commentSection = CommentSection(args.commentSectionId, arrayListOf())

        title.text = args.postTitle
        userName.text = args.postUsername
        bes.text = args.postBes

        val adapter = CommentsAdapter(requireContext(), arrayListOf(),
            object : CommentsAdapter.OnItemClickListener {
                override fun onItemClick(item: Comment?, position: Int, type: Int, parent : Comment?) {
                    if (type == 0) {
                        view.findViewById<FrameLayout>(R.id.postFragComField).visibility = View.VISIBLE
                        view.findViewById<FrameLayout>(R.id.postFragMain).visibility = View.GONE
                        tempParent = item
                    } else {
                        likeComment(item!!, item.sectionId)
                    }
                }
            })
        comRec.layoutManager = LinearLayoutManager(requireContext())
        comRec.adapter = adapter
        getComments(commentSection, adapter)

        refresh.setOnRefreshListener {
            refreshComments(commentSection, adapter, refresh)
        }

        comBtn.setOnClickListener {
            view.findViewById<FrameLayout>(R.id.postFragComField).visibility = View.GONE
            view.findViewById<FrameLayout>(R.id.postFragMain).visibility = View.VISIBLE
            val com = Comment(comBigTxt.text.toString(), currentUser, commentSection.id)
            if (tempParent != null) uploadComment(com, commentSection, tempParent)
            else uploadComment(com, commentSection, null)
            comBigTxt.text.clear()
        }

        comTxt.setOnClickListener {
            view.findViewById<FrameLayout>(R.id.postFragComField).visibility = View.VISIBLE
            view.findViewById<FrameLayout>(R.id.postFragMain).visibility = View.GONE
        }
    }
}