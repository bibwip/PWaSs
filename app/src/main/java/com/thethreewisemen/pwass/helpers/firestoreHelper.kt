package com.thethreewisemen.pwass.helpers

import android.util.Log
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.thethreewisemen.pwass.adapters.CommentsAdapter
import com.thethreewisemen.pwass.adapters.RecyclerAdapter
import com.thethreewisemen.pwass.objects.Comment
import com.thethreewisemen.pwass.objects.CommentSection
import com.thethreewisemen.pwass.objects.Post
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.collections.ArrayList

const val TAG = "FIRESTORE"
const val postsCol = "Posts"
const val comSecCol = "CommentSection"


fun refreshPosts(adapter : RecyclerAdapter, refresher : SwipeRefreshLayout){
    val posts = arrayListOf<Post>()
    val query = Firebase.firestore.collection(postsCol)
    query.orderBy("datum", Query.Direction.DESCENDING).get().addOnSuccessListener { snapshot ->
        for (doc in snapshot) {
            posts.add(doc.toObject(Post::class.java))
        }
        adapter.updateItems(posts)
        refresher.isRefreshing = false
    }.addOnFailureListener {
        Log.d(TAG, "failed getting all posts")
    }
}

fun uploadPost(post: Post)  {
    val db = Firebase.firestore
    db.collection(postsCol).add(post).addOnSuccessListener {
        Log.d(TAG, "Post geupload: \nid= ${post.id}\ntitel=${post.titel}\nuser=${post.naam_poster}")
    }.addOnFailureListener {
        Log.d(
            TAG, "Post niet geupload: : \n" +
                    "id= ${post.id}\n" +
                    "titel=${post.titel}\n" +
                    "user=${post.naam_poster}"
        )
    }
}

fun getPosts(adapter: RecyclerAdapter){
    val posts = arrayListOf<Post>()
    val query = Firebase.firestore.collection(postsCol)
    query.orderBy("datum", Query.Direction.DESCENDING).get().addOnSuccessListener { snapshot ->
        for (doc in snapshot) {
            posts.add(doc.toObject(Post::class.java))
        }
        adapter.updateItems(posts)
    }.addOnFailureListener {
        Log.d(TAG, "failed getting all posts")
    }
}
fun refreshComments(section : CommentSection, adapter: CommentsAdapter, refresher: SwipeRefreshLayout) = runBlocking{
    launch {
        val db = Firebase.firestore
        section.comments.clear()
        db.collection(comSecCol).document(section.id).collection("comments").get()
            .addOnSuccessListener {  snapshot ->
                for (item in snapshot) {
                    val comment = item.toObject(Comment::class.java)
                    comment.sectionId = section.id
                    comment.fireId = item.id
                    getChild(comment, section, arrayListOf())
                    section.comments.add(comment)
                }
                adapter.updateItems(section.comments)
                refresher.isRefreshing = false
            }
            .addOnFailureListener {
                Log.d(TAG, "failed getting comments: ${it.message}")
            }
        Log.d(TAG, "updated items: ${section.comments}")

    }
}

fun getComments(section : CommentSection, adapter: CommentsAdapter) = runBlocking{
    launch {
        val db = Firebase.firestore
        section.comments.clear()
        db.collection(comSecCol).document(section.id).collection("comments")
            .orderBy("likes", Query.Direction.DESCENDING).get().addOnSuccessListener {  snapshot ->
                for (item in snapshot) {
                    val comment = item.toObject(Comment::class.java)
                    comment.sectionId = section.id
                    comment.fireId = item.id
                    getChild(comment, section, arrayListOf())
                    section.comments.add(comment)
                }
                adapter.updateItems(section.comments)
            }
            .addOnFailureListener {
                Log.d(TAG, "failed getting comments: ${it.message}")
            }
        Log.d(TAG, "updated items: ${section.comments}")

    }
}

private fun getChild(comment: Comment, section: CommentSection , index : ArrayList<Int>)  {
    for ((count, com) in comment.child.withIndex()) {
        if  (comment.fireId == "") {
            com.parent = comment.parent
        } else {
            com.parent = comment.fireId
        }
        com.childid = index.clone() as ArrayList<Int>
        com.childid.add(count)
        com.sectionId = section.id
        if (com.child.isNotEmpty()) {
            getChild(com, section, com.childid)
        }
    }
}

fun likeComment(comment: Comment, comSecId: String){
    val db = Firebase.firestore
    if (comment.parent == null){
        comment.likes++
        db.collection(comSecCol).document(comSecId).collection("comments")
            .document(comment.fireId).set(comment)
    } else {
        db.collection(comSecCol).document(comSecId).collection("comments")
            .document(comment.parent!!).get().addOnSuccessListener {
                val com = it.toObject(Comment::class.java)
                likeChild(comment, com!!, comment.childid)
                db.collection(comSecCol).document(comSecId).collection("comments").document(it.id).set(com)
            }
    }

}

private fun likeChild(child: Comment, parent: Comment, indexs: ArrayList<Int>){
    if (indexs.isNotEmpty()){
        val index = indexs[0]
        indexs.removeAt(0)
        likeChild(child, parent.child[index], indexs)
    } else {
        parent.likes++
    }
}


fun uploadComment(comment: Comment, comSec : CommentSection, parent : Comment?) {
    val db = Firebase.firestore
    if (parent != null){
        if (parent.parent != null) {
            for (com in comSec.comments) {
                Log.d(TAG, com.fireId + " === " + parent.parent)
                if (com.fireId == parent.parent) {
                    uploadChildComment(parent.childid, comment, com, comSec.id )
                    db.collection(comSecCol).document(comSec.id).collection("comments").document(com.fireId).set(com)
                    Log.d(TAG, "uploaded comment: $com")
                }
            }
        } else {
            for (com in comSec.comments) {
                Log.d(TAG, com.fireId + " == " + parent.fireId)
                if (com.fireId == parent.fireId) {
                    com.child.add(comment)
                    db.collection(comSecCol).document(comSec.id).collection("comments").document(parent.fireId).set(com)
                    Log.d(TAG, "uploaded comment: $com")
                }
            }
        }

    } else {
        db.collection(comSecCol).document(comSec.id).collection("comments").add(comment)
        Log.d(TAG, "uploaded paren comment: $comment")
    }
}

private fun uploadChildComment(indexs: ArrayList<Int>, comment: Comment, parent: Comment, comSecId: String) {
    Log.d(TAG, indexs.toString())
    if (indexs.isNotEmpty()){
        val index = indexs[0]
        indexs.removeAt(0)
        uploadChildComment(indexs, comment, parent.child[index], comSecId)
    } else {
        parent.child.add(comment)
    }
}


