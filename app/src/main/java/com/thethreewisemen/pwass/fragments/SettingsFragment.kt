package com.thethreewisemen.pwass.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceCategory
import androidx.preference.SwitchPreference
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.google.type.DateTime
import com.thethreewisemen.pwass.MainActivity
import com.thethreewisemen.pwass.MainActivity.Companion.COLORBACK
import com.thethreewisemen.pwass.MainActivity.Companion.COLORPOST
import com.thethreewisemen.pwass.MainActivity.Companion.COLORPRIMARY
import com.thethreewisemen.pwass.MainActivity.Companion.COLORPRIMARYVAR
import com.thethreewisemen.pwass.MainActivity.Companion.USERNAME
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.objects.ButtonPreference
import com.thethreewisemen.pwass.objects.ColorPreference
import com.thethreewisemen.pwass.objects.CustomPrefCat
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat


class SettingsFragment : PreferenceFragmentCompat() {

    lateinit var prefs : SharedPreferences

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        prefs = requireActivity().getSharedPreferences(MainActivity.PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
        DateTime.getDefaultInstance()
        val main = activity as MainActivity

        val prefCustomTheme = findPreference<SwitchPreference>("customTheme")
        val colorCat = findPreference<CustomPrefCat>("colorCat")!!
        val prefApply = findPreference<ButtonPreference>("applyTheme")!!
        val prefColPri = findPreference<ColorPreference>("colorPrimary")!!
        val prefColSec = findPreference<ColorPreference>("colorSec")!!
        val prefColBack = findPreference<ColorPreference>("colorBack")!!
        val prefColPost = findPreference<ColorPreference>("colorPost")!!
        val prefUsername = findPreference<EditTextPreference>("userName")!!



        colorCat.setColor(Color.parseColor(main.colorPrimary))
        prefApply.setColor(Color.parseColor(main.colorPrimary))

        prefUsername.text = prefs.getString(USERNAME, "")


        findPreference<PreferenceCategory>("colorCat")!!.isVisible = prefCustomTheme!!.isChecked

        prefApply.setClickListener(View.OnClickListener {
            val refresh = Intent(activity, MainActivity::class.java)
            startActivity(refresh)
        })

        prefCustomTheme.setOnPreferenceChangeListener { _, n ->
           colorCat.isVisible = !colorCat.isVisible
            prefs.edit().putBoolean(MainActivity.HASCUSTOMTHEME, n.toString().toBoolean()).apply()
            true
        }


        prefColPri.setColor(Color.parseColor(main.colorPrimary))
        prefColSec.setColor(Color.parseColor(main.colorPrimaryVariant))
        prefColPost.setColor(Color.parseColor(main.colorPost))
        prefColBack.setColor(Color.parseColor(main.colorPrimary))


        prefColPri.setOnPreferenceClickListener {
            ColorPickerDialogBuilder
                .with(requireActivity())
                .setTitle(R.string.PickAColour)
                .initialColor(Color.parseColor(main.colorPrimary))
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(12)
                .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                    dialog.cancel()
                }.setPositiveButton(android.R.string.ok) { d, lastSelectedColor, _ ->
                prefColPri.setColor(lastSelectedColor)
                val col = "#" + Integer.toHexString(lastSelectedColor)
                prefs.edit().putString(COLORPRIMARY, col).apply()
                main.colorPrimary = col
                d.cancel()
            }
                .build()
                .show()
            true
        }
        prefColSec.setOnPreferenceClickListener {
            ColorPickerDialogBuilder
                .with(requireActivity())
                .setTitle(R.string.PickAColour)
                .initialColor(Color.parseColor(main.colorPrimaryVariant))
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(12)
                .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                    dialog.cancel()
                }.setPositiveButton(android.R.string.ok) { d, lastSelectedColor, _ ->
                    prefColSec.setColor(lastSelectedColor)
                    val col = "#" + Integer.toHexString(lastSelectedColor)
                    prefs.edit().putString(COLORPRIMARYVAR, col).apply()
                    main.colorPrimaryVariant = col
                    d.cancel()
                }
                .build()
                .show()
            true
        }
        prefColBack.setOnPreferenceClickListener {
            ColorPickerDialogBuilder
                .with(requireActivity())
                .setTitle(R.string.PickAColour)
                .initialColor(Color.parseColor(main.colorBack))
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(12)
                .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                    dialog.cancel()
                }.setPositiveButton(android.R.string.ok) { d, lastSelectedColor, _ ->
                    prefColBack.setColor(lastSelectedColor)
                    val col = "#" + Integer.toHexString(lastSelectedColor)
                    prefs.edit().putString(COLORBACK, col).apply()
                    main.colorBack = col
                    d.cancel()
                }
                .build()
                .show()
            true
        }
        prefColPost.setOnPreferenceClickListener {
            ColorPickerDialogBuilder
                .with(requireActivity())
                .setTitle(R.string.PickAColour)
                .initialColor(Color.parseColor(main.colorPost))
                .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                .density(12)
                .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                    dialog.cancel()
                }.setPositiveButton(android.R.string.ok) { d, lastSelectedColor, _ ->
                    prefColPost.setColor(lastSelectedColor)
                    val col = "#" + Integer.toHexString(lastSelectedColor)
                    prefs.edit().putString(COLORPOST, col).apply()
                    main.colorPost = col
                    d.cancel()
                }
                .build()
                .show()
            true
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        val prefUsername = findPreference<EditTextPreference>("userName")
        prefs.edit().putString(USERNAME, prefUsername!!.text.toString()).apply()
    }
}