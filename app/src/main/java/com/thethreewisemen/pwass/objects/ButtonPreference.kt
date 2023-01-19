package com.thethreewisemen.pwass.objects


import android.content.Context
import android.util.AttributeSet
import android.view.View.OnClickListener
import android.widget.Button
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.thethreewisemen.pwass.R


class ButtonPreference(context: Context, attrs: AttributeSet) :
    Preference(context, attrs) {
    private var button: Button? = null
    private var listener: OnClickListener? = null
    private var bcolor= 0xFFFFF00


    //onBindViewHolder() will be called after we call setImageClickListener() from SettingsFragment
    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)
        button = holder.findViewById(R.id.prefButton) as Button?
        button?.setOnClickListener(listener)
        button?.setBackgroundColor(bcolor)

    }

    fun setClickListener(listen : OnClickListener) {
       listener = listen
    }

    fun setColor(color : Int) {
        bcolor = color
        notifyChanged()
    }
}