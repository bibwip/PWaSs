package com.thethreewisemen.pwass.Items

import android.graphics.Bitmap

data class Post(val ID: Int, val titel: String, var beschrijving: String, val img: Bitmap,
                val naam_poster: String, val comments: Any, var like: Int)
