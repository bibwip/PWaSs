package com.thethreewisemen.pwass.objects

data class Comment(
    val id : Int,
    val child : ArrayList<Comment>,
    var text : String,
    var userName: String,
    var likes: Int
    )