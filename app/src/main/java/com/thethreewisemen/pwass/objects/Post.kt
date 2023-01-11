package com.thethreewisemen.pwass.objects

import android.graphics.Bitmap
import android.os.Parcelable
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.thethreewisemen.pwass.firestore.comSecCol
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

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

    override fun toString(): String {
        return "Post:\n" +
                "id: $id\n" +
                "titel: $titel\n" +
                "beschrijving $beschrijving\n" +
                "naam poster: $naam_poster\n" +
                "likes: $like"
    }
}
