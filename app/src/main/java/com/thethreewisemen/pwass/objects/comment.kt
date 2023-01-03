package com.thethreewisemen.pwass.objects


data class Comment(
    var parent: Any? = null,
    var fireId: String = "",
    var sectionId: String = "",
    var childid: ArrayList<Int> = arrayListOf(),
    val child : ArrayList<Comment> = arrayListOf(),
    var text : String = "",
    var userName: String = "",
    var likes: Int = 0
    ) {
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