package com.thethreewisemen.pwass

import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController


class MainActivity : AppCompatActivity() {

    var hasCustomTheme = false
    var colorPrimary = ""
    var colorPrimaryVariant = ""

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_graph_container) as NavHostFragment
        navController = navHostFragment.findNavController()

        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        prefs.edit().putBoolean(HASCUSTOMTHEME, true).apply()
        prefs.edit().putString(COLORPRIMARY, "#34eb3d").apply()
        prefs.edit().putString(COLORPRIMARYVAR, "#eb34d8").apply()
        if (prefs.getBoolean(HASCUSTOMTHEME, false)) {
            hasCustomTheme = true
            Log.d(TAG, "has custom theme")
            setColors(prefs)
            Log.d(TAG, "prim col: $colorPrimary")
        }
        //checkFirstRun(prefs)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(colorPrimary)))
        window.statusBarColor = Color.parseColor(colorPrimaryVariant)

    }

    private fun setColors(prefs: SharedPreferences){
        colorPrimary = prefs.getString(COLORPRIMARY, "#eb4034")!!
        colorPrimaryVariant = prefs.getString(COLORPRIMARYVAR, "#eb4034")!!

    }

//    private fun checkFirstRun(prefs : SharedPreferences) {
//        // pakt de huidige versie en de bewaarde versie codes
//        val currentVersionCode = BuildConfig.VERSION_CODE
//        val savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST)
//
//        if (currentVersionCode == savedVersionCode) {
//            // niks nieuws
//            return
//        } else if (savedVersionCode == DOESNT_EXIST || currentVersionCode > savedVersionCode) {
//            // app voor het eerst geopend of nieuwe update
//            //navController.navigate(R.id.)
//        }
//        // bewaar huidige versie code
//        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply()
//    }

    companion object {
        const val TAG = "epic"

        const val PREF_VERSION_CODE_KEY = "version_code"
        const val DOESNT_EXIST = -1
        const val PREFS_NAME = "MyPrefsFile"

        private const val HASCUSTOMTHEME = "isCustomTheme"
        private const val COLORPRIMARY = "colorPrimary"
        private const val COLORPRIMARYVAR = "colorPrimaryVar"

    }

}