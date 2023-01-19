package com.thethreewisemen.pwass.objects

import android.R
import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceViewHolder


class CustomPrefCat : PreferenceCategory {

    private var bcolor = 0xFFFF000

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(
        context: Context, attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle)

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)
        val titleView = holder.findViewById(R.id.title) as TextView
        titleView.setTextColor(bcolor)
    }

    fun setColor(color : Int) {
        bcolor = color
        notifyChanged()
    }
}