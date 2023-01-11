package com.thethreewisemen.pwass.objects

data class CommentSection(
    var id : String = "",
    val comments : ArrayList<Comment> = arrayListOf(),

)