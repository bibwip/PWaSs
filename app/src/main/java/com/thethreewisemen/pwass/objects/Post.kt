package com.thethreewisemen.pwass.objects

import android.graphics.Bitmap
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.thethreewisemen.pwass.firestore.comSecCol

data class Post(
    var id: String = "",
    val titel: String = "",
    var beschrijving: String = "",
    val naam_poster: String = "",
    val commentSection: CommentSection = CommentSection(),
    var like: Int = 0,
    val img: Bitmap? = null
) {
    constructor(titel : String, beschrijving: String, poster: String, img: Bitmap?) :
            this("", titel, beschrijving, poster, CommentSection(), 0, img)

    init {
        val db = Firebase.firestore
        db.collection(comSecCol).add(commentSection).addOnSuccessListener {
            id = it.id
        }
    }
    override fun toString(): String {
        return "Post:\n" +
                "id: $id\n" +
                "titel: $titel\n" +
                "beschrijving $beschrijving\n" +
                "naam poster: $naam_poster\n" +
                "likes: $like"
    }
}
