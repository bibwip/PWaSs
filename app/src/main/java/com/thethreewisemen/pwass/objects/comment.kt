package com.thethreewisemen.pwass.objects

import android.graphics.Bitmap


data class Comment(
    var parent: Any? = null,
    var fireId: String = "",
    var sectionId: String = "",
    var childid: MutableList<Int> = mutableListOf(),
    val child : MutableList<Comment> = mutableListOf(),
    var text : String = "",
    var userName: String = "",
    var likes: Int = 0
    ) {

    constructor(text : String, username: String, secId: String) :
            this(null, "", secId, mutableListOf(), mutableListOf(), text, username, 0)

    override fun toString(): String {
        return "comment:\n" +
                "parent:    $parent\n" +
                "fireid:    $fireId\n" +
                "sectionId: $sectionId\n" +
                "childId:   $childid\n" +
                "child:     $child\n" +
                "text:      $text\n" +
                "userName:  $userName\n" +
                "likes:     $likes"
    }
}