package com.thethreewisemen.pwass.fragments

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.CheckBoxPreference
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.thethreewisemen.pwass.MainActivity
import com.thethreewisemen.pwass.R
import com.thethreewisemen.pwass.objects.ColorPreference
import com.thethreewisemen.pwass.MainActivity.Companion.COLORBACK
import com.thethreewisemen.pwass.MainActivity.Companion.COLORPRIMARYVAR
import com.thethreewisemen.pwass.MainActivity.Companion.COLORPOST
import com.thethreewisemen.pwass.MainActivity.Companion.COLORPRIMARY


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val prefs = requireActivity().getSharedPreferences(MainActivity.PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
        val main = activity as MainActivity

        val prefCustomTheme = findPreference<CheckBoxPreference>("customTheme")
        val prefColPri = findPreference<ColorPreference>("colorPrimary")
        val prefColSec = findPreference<ColorPreference>("colorSec")
        val prefColBack = findPreference<ColorPreference>("colorBack")
        val prefColPost = findPreference<ColorPreference>("colorPost")

        findPreference<PreferenceCategory>("colorCat")!!.isVisible = prefCustomTheme!!.isChecked

        prefCustomTheme.setOnPreferenceChangeListener { preference, _ ->
            findPreference<PreferenceCategory>("colorCat")!!.isVisible =
                !findPreference<PreferenceCategory>("colorCat")!!.isVisible

            prefs.edit().putBoolean(MainActivity.HASCUSTOMTHEME, preference.isEnabled).apply()

            true
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