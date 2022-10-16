package com.example.mcchallengeapp.data.source

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * To save and fetch data from SharedPreferences
 */
class SharedPreferencesCustom @Inject constructor(@ApplicationContext val context: Context) {

    private var prefs: SharedPreferences =
        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    companion object {
        const val LANGUAGE = "language"
    }

    /**
     * Save language selected
     */
    fun saveSelectedLanguage(language: String) {
        val editor = prefs.edit()
        editor.putString(LANGUAGE, language)
        editor.apply()
    }

    fun fetchLanguage(): String {
        return prefs.getString(LANGUAGE, "es").toString()
    }
}