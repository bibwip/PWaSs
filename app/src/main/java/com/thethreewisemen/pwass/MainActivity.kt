package com.thethreewisemen.pwass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_graph_container) as NavHostFragment
        navController = navHostFragment.findNavController()

    }

    private fun checkFirstRun() {
        // pakt de huidige versie en de bewaarde versie codes
        val currentVersionCode = BuildConfig.VERSION_CODE
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val savedVersionCode = prefs!!.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST)

        if (currentVersionCode == savedVersionCode) {
            // niks nieuws
            return
        } else if (savedVersionCode == DOESNT_EXIST || currentVersionCode > savedVersionCode) {
            // app voor het eerst geopend of nieuwe update
            //navController.navigate(R.id.)
        }
        // bewaar huidige versie code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply()
    }

    companion object {
        const val PREF_VERSION_CODE_KEY = "version_code"
        const val DOESNT_EXIST = -1
        const val PREFS_NAME = "MyPrefsFile"
        const val TAG = "MAIN"
    }

}