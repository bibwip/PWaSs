package com.thethreewisemen.pwass.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.CheckBoxPreference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.thethreewisemen.pwass.MainActivity
import com.thethreewisemen.pwass.MainActivity.Companion.COLORBACK
import com.thethreewisemen.pwass.MainActivity.Companion.COLORPOST
import com.thethreewisemen.pwass.MainActivity.Companion.COLORPRIMARY
import com.thethreewisemen.pwass.MainActivity.Companion.COLORPRIMARYVAR
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.objects.ButtonPreference
import com.thethreewisemen.pwass.objects.ColorPreference


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val prefs = requireActivity().getSharedPreferences(MainActivity.PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
        val main = activity as MainActivity

        val prefCustomTheme = findPreference<SwitchPreference>("customTheme")
        val prefApply = findPreference<ButtonPreference>("applyTheme")
        val prefColPri = findPreference<ColorPreference>("colorPrimary")
        val prefColSec = findPreference<ColorPreference>("colorSec")
        val prefColBack = findPreference<ColorPreference>("colorBack")
        val prefColPost = findPreference<ColorPreference>("colorPost")

        findPreference<PreferenceCategory>("colorCat")!!.isVisible = prefCustomTheme!!.isChecked

        prefApply?.setClickListener(View.OnClickListener {
            val refresh = Intent(activity, MainActivity::class.java)
            startActivity(refresh)
        })

        prefCustomTheme.setOnPreferenceChangeListener { preference, n ->
            findPreference<PreferenceCategory>("colorCat")!!.isVisible =
                !findPreference<PreferenceCategory>("colorCat")!!.isVisible
            prefs.edit().putBoolean(MainActivity.HASCUSTOMTHEME, n.toString().toBoolean()).apply()
            true
        }

        if (prefCustomTheme.isChecked){
            prefColPri!!.setColor(Color.parseColor(main.colorPrimary))
        }

        prefColPri!!.setOnPreferenceClickListener {
            ColorPickerDialogBuilder
                .with(requireActivity())
                .setTitle(R.string.pickColor)
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
        prefColSec!!.setOnPreferenceClickListener {
            ColorPickerDialogBuilder
                .with(requireActivity())
                .setTitle(R.string.pickColor)
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
        prefColBack!!.setOnPreferenceClickListener {
            ColorPickerDialogBuilder
                .with(requireActivity())
                .setTitle(R.string.pickColor)
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
        prefColPost!!.setOnPreferenceClickListener {
            ColorPickerDialogBuilder
                .with(requireActivity())
                .setTitle(R.string.pickColor)
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
}