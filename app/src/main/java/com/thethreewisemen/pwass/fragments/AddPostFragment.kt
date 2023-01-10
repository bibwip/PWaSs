package com.thethreewisemen.pwass.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.firestore.uploadPost
import com.thethreewisemen.pwass.objects.Post

class AddPostFragment : Fragment(R.layout.fragment_add_post) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titel = view.findViewById<EditText>(R.id.addPostTitelEt)
        val beschrijving = view.findViewById<EditText>(R.id.addPostBesEt)
        val uploadBtn = view.findViewById<Button>(R.id.addPostUploadBtn)

        uploadBtn.setOnClickListener {
            uploadPost(Post(titel.text.toString(), beschrijving.text.toString(), "", null))
            val action = AddPostFragmentDirections.actionAddPostFragmentToMainFragment()
            view.findNavController().navigate(action)
        }

    }

}