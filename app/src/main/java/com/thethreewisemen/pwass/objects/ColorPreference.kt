package com.thethreewisemen.pwass.objects


import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.thethreewisemen.pwass.R


class ColorPreference(context: Context, attrs: AttributeSet) :
    Preference(context, attrs) {
    private var imageView: ImageView? = null
    private var textView: TextView? = null

    //onBindViewHolder() will be called after we call setImageClickListener() from SettingsFragment
    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)
        imageView = holder.findViewById(R.id.prefsColorImg) as ImageView?
        textView = holder.findViewById(R.id.prefsColorText) as TextView?
        imageView?.setBackgroundColor(0xFFFF000)
        textView?.text = title
    }

    fun setColor(color : Int) {
        imageView!!.setImageDrawable(ColorDrawable(color))
        notifyChanged()
    }
}