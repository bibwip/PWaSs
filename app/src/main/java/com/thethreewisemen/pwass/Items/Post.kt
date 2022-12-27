package com.thethreewisemen.pwass.Items

import android.graphics.Bitmap

data class Post(
    val id: Int? = null,
    val titel: String? = null,
    var beschrijving: String? = null,
    val img: Bitmap? = null,
    val naam_poster: String? = null,
    val comments: Any? = null,
    var like: Int? = null
) {

    override fun toString(): String {
        return "Post:\n" +
                "id: $id\n" +
                "titel: $titel\n" +
                "beschrijving $beschrijving\n" +
                "naam poster: $naam_poster\n" +
                "likes: $like"
    }
}
