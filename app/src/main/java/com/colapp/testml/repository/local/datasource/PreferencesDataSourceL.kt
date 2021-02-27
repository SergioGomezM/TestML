package com.colapp.testml.repository.local.datasource

import android.content.SharedPreferences
import com.colapp.testml.repository.local.LocalCallback
import com.colapp.testml.repository.RepoConst.DEFAULT_SITE
import com.colapp.testml.repository.RepoConst.ERROR_EXCEPTION
import com.colapp.testml.repository.RepoConst.KEY_PREF_SELECTED_SITE
import com.colapp.testml.repository.RepoConst.LOCAL_ORIGIN
import com.colapp.testml.repository.RepoConst.MESSAGE_EXCEPTION
import com.colapp.testml.repository.ResRepository
import com.colapp.testml.util.Log

class PreferencesDataSourceL private constructor(sharedPreferences: SharedPreferences) {

    private var sharedPreferences: SharedPreferences

    init {
        this.sharedPreferences = sharedPreferences
    }

    companion object {

        private var mInstance: PreferencesDataSourceL? = null

        fun initializer(sharedPreferences: SharedPreferences): PreferencesDataSourceL {
            if (mInstance == null) {
                mInstance = PreferencesDataSourceL(sharedPreferences)
            }
            return mInstance as PreferencesDataSourceL
        }

        fun getInstance(): PreferencesDataSourceL {
            return mInstance as PreferencesDataSourceL
        }

    }

    fun setSelectedSite(site: String, callback: LocalCallback<String>) {
        try {
            sharedPreferences.edit().putString(KEY_PREF_SELECTED_SITE, site).apply()
            getSelectedSite(callback)
        } catch (ex: Exception) {
            Log.error(ex.message)
            val res = ResRepository<String>(LOCAL_ORIGIN)
            res.errorCode = ERROR_EXCEPTION
            res.message = MESSAGE_EXCEPTION
            callback.onResult(res, null)
        }
    }

    fun getSelectedSite(callback: LocalCallback<String>) {
        val res = ResRepository<String>(LOCAL_ORIGIN)
        try {
            res.data = sharedPreferences.getString(KEY_PREF_SELECTED_SITE, "")
            if (res.data?.isEmpty() == true) {
                Log.warn(res.data)
                res.data = DEFAULT_SITE
            }
        } catch (ex: Exception) {
            Log.error(ex.message)
            res.errorCode = ERROR_EXCEPTION
            res.message = MESSAGE_EXCEPTION
        }
        callback.onResult(res, null)
    }
}