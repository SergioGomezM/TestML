package com.colapp.testml.initializer

import android.content.Context
import androidx.startup.Initializer
import com.colapp.testml.BuildConfig
import com.colapp.testml.repository.local.datasource.PreferencesDataSourceL

/**
this class is used by the startup-runtime library to initialize SharedPreferences
 */
class PreferencesInitializer : Initializer<PreferencesDataSourceL> {
    override fun create(context: Context): PreferencesDataSourceL {
        val preferences = context.getSharedPreferences(BuildConfig.SH_PREFERENCE, Context.MODE_PRIVATE)
        return PreferencesDataSourceL.initializer(preferences)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return emptyList<Class<out Initializer<*>>>().toMutableList()
    }
}