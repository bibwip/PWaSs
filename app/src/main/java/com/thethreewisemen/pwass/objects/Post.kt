package com.thethreewisemen.pwass.objects

import android.graphics.Bitmap


data class Post(
    var id: String = "",
    val titel: String = "",
    var beschrijving: String = "",
    val naam_poster: String = "",
    val commentSection: CommentSection = CommentSection(),
    var like: Int = 0,
    val img: Bitmap? = null,
    val datum: Long = System.currentTimeMillis()
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

    fun getDate() : String {
        val seconden = (System.currentTimeMillis() - datum)/1000
        val minuten = seconden/60
        val uren = minuten/60
        val dagen = uren/24

        if (dagen >= 1) {
            return "${dagen.toInt()}d"
        } else if (uren >= 1) {
            return "${uren.toInt()}h"
        } else if (minuten >= 1){
            return "${minuten.toInt()}m"
        }
        return "${seconden.toInt()}s"
    }
}
