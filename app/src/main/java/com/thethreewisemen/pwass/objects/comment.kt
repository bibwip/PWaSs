package com.thethreewisemen.pwass.objects


data class Comment(
    var parent: String? = null,
    var fireId: String = "",
    var sectionId: String = "",
    var childid: ArrayList<Int> = arrayListOf(),
    val child : ArrayList<Comment> = arrayListOf(),
    var text : String = "",
    var userName: String = "",
    var likes: Int = 0
    ) {

    constructor(text : String, username: String, secId: String) :
            this(null, "", secId, arrayListOf(), arrayListOf(), text, username, 0)

    override fun toString(): String {
        return "\n" +
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