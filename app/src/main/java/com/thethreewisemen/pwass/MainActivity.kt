package com.thethreewisemen.pwass

import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected


class MainActivity : AppCompatActivity() {

    var hasCustomTheme = false
    var colorPrimary = ""
    var colorPrimaryVariant = ""
    var colorBack = ""
    var colorPost = ""

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_graph_container) as NavHostFragment
        navController = navHostFragment.findNavController()

        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        hasCustomTheme = (prefs.getBoolean(HASCUSTOMTHEME, false))
        setColors(prefs)
        //checkFirstRun(prefs)

        if (hasCustomTheme){
            supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(colorPrimary)))
            window.statusBarColor = Color.parseColor(colorPrimaryVariant)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    private fun setColors(prefs: SharedPreferences){

        colorPrimary = prefs.getString(COLORPRIMARY, "#eb40ff34")!!
        colorPrimaryVariant = prefs.getString(COLORPRIMARYVAR, "#eb403ff4")!!
        colorBack = prefs.getString(COLORBACK, "#eb40ff34")!!
        colorPost = prefs.getString(COLORPOST, "#eb4f0f34")!!

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

        const val USERNAME = "userName"
        const val HASCUSTOMTHEME = "isCustomTheme"
        const val COLORPRIMARY = "colorPrimary"
        const val COLORPRIMARYVAR = "colorPrimaryVar"
        const val COLORBACK = "colorBack"
        const val COLORPOST = "colorPost"

    }

}