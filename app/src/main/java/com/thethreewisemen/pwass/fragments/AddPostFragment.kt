package com.thethreewisemen.pwass.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.preference.EditTextPreference
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.type.Date
import com.thethreewisemen.pwass.MainActivity
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.firestore.comSecCol
import com.thethreewisemen.pwass.firestore.uploadPost
import com.thethreewisemen.pwass.objects.Post
import java.time.LocalDate

class AddPostFragment : Fragment(R.layout.fragment_add_post) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titel = view.findViewById<EditText>(R.id.addPostTitelEt)
        val beschrijving = view.findViewById<EditText>(R.id.addPostBesEt)
        val uploadBtn = view.findViewById<Button>(R.id.addPostUploadBtn)
        val userName = view.findViewById<EditText>(R.id.addPostUsername)

        val prefs = requireActivity().getSharedPreferences(MainActivity.PREFS_NAME, AppCompatActivity.MODE_PRIVATE)

        userName.setText(prefs.getString(MainActivity.USERNAME, ""))

        uploadBtn.setOnClickListener {
            val post = Post(titel.text.toString(), beschrijving.text.toString(), userName.text.toString(),
                null)
            val db = Firebase.firestore
            db.collection(comSecCol).add(post.commentSection).addOnSuccessListener {
                post.commentSection.id = it.id
                uploadPost(post)

            }
            prefs.edit().putString(MainActivity.USERNAME, userName.text.toString()).apply()
            val refresh = Intent(activity, MainActivity::class.java)
            startActivity(refresh)
            val action = AddPostFragmentDirections.actionAddPostFragmentToMainFragment()
            view.findNavController().navigate(action)

        }
        val main = (activity as MainActivity)

        if (main.hasCustomTheme) {
            uploadBtn.background.setTint(Color.parseColor(main.colorPrimary))
        }
    }

}