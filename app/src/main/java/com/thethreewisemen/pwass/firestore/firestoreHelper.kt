package com.thethreewisemen.pwass.firestore

import android.util.Log
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.thethreewisemen.pwass.Items.Post

val TAG = "FIRESTORE"
val postsCol = "Posts"

fun uploadPost(post: Post) : Boolean {
    val db = Firebase.firestore
    var succes = false
    db.collection(postsCol).add(post).addOnSuccessListener {
        Log.d(TAG, "Post geupload: \nid= ${post.id}\ntitel=${post.titel}\nuser=${post.naam_poster}")
        succes = true
    }.addOnFailureListener {
        Log.d(
            TAG, "Post niet geupload: : \n" +
                    "id= ${post.id}\n" +
                    "titel=${post.titel}\n" +
                    "user=${post.naam_poster}"
        )
    }
    return succes
}

fun getPost(id: Int){
    val db = Firebase.firestore
    var post : Post ?= null

    db.collection(postsCol).whereEqualTo("id",  id).get().addOnSuccessListener { documents ->
        for (document in documents) {
            post = document.toObject(Post::class.java)
            Log.d(TAG, post.toString())
        }
        Log.d(TAG, "Retrieved post with id: ${id} and title: ${post?.titel}")
    }.addOnFailureListener {
        Log.d(TAG, "Failed retrieving post with id: ${id}")
    }
}

fun getPosts(adapter : Any){
    val posts = arrayListOf<Post>()
    val query = Firebase.firestore.collection(postsCol)
    query.get().addOnSuccessListener { snapshot ->
        for (doc in snapshot) {
            posts.add(doc.toObject(Post::class.java))
        }
        //TODO adapter updaten
    }.addOnFailureListener {
        Log.d(TAG, "failed getting all posts")
        //TODO adapter waarschuwen
    }
}

