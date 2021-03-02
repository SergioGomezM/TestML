package com.colapp.testml.initializer

import android.content.Context
import androidx.startup.Initializer
import com.colapp.testml.repository.local.DataBase

/**
this class is used by the startup-runtime library to initialize the databases
 */
class DataBaseInitializer: Initializer<DataBase> {
    override fun create(context: Context): DataBase {
        return DataBase.initializer(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return emptyList<Class<out Initializer<*>>>().toMutableList()
    }
}