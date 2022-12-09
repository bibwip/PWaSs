package com.thethreewisemen.pwass.firestore

import android.util.Log
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.thethreewisemen.pwass.Items.Post

val TAG = "FIRESTORE"
val postsCol = "Posts"

fun uploadPost(post: Post) : Boolean {
    val db = Firebase.firestore
    var succes = false
    db.collection(postsCol).add(post).addOnSuccessListener {
        Log.d(TAG, "Post geupload: \nid= ${post.ID}\ntitel=${post.titel}\nuser=${post.naam_poster}")
        succes = true
    }.addOnFailureListener {
        Log.d(
            TAG, "Post niet geupload: : \n" +
                    "id= ${post.ID}\n" +
                    "titel=${post.titel}\n" +
                    "user=${post.naam_poster}"
        )
    }
    return succes
}

fun getPost(id: Int) : Post? {
    val db = Firebase.firestore
    var post : Post ?= null

    db.collection(postsCol).whereEqualTo("ID", id).get().addOnSuccessListener { document ->
        post = document.toObjects<Post>()[0]
        Log.d(TAG, "Retrieved post with id: ${id}")
    }.addOnFailureListener {
        Log.d(TAG, "Failed retrieving post with id: ${id}")
    }

    return post
}

fun getPosts() {
    val query = Firebase.firestore.collection(postsCol).orderBy("ID", Query.Direction.DESCENDING)
}

