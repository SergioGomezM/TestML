package com.colapp.testml.repository.controller

import androidx.lifecycle.MutableLiveData
import com.colapp.testml.model.Site
import com.colapp.testml.repository.RepoConst
import com.colapp.testml.repository.local.LocalCallback
import com.colapp.testml.repository.ResRepository
import com.colapp.testml.repository.controller.mapper.toSite
import com.colapp.testml.repository.controller.mapper.toSiteLocal
import com.colapp.testml.repository.local.datasource.PreferencesDataSourceL
import com.colapp.testml.repository.local.datasource.SitesDataSourceL
import com.colapp.testml.repository.remote.RemoteCallback
import com.colapp.testml.repository.remote.mercadolibre.datasource.SitesDataSourceR
import com.colapp.testml.repository.remote.mercadolibre.response.RemoteSite
import com.colapp.testml.util.Log
import retrofit2.Response

class SiteController {

    private val preferenceDataSourceL = PreferencesDataSourceL.getInstance()
    private val sitesDataSourceL = SitesDataSourceL.getInstance()
    private val sitesDataSourceR = SitesDataSourceR.getInstance()

    val sites = MutableLiveData<ResRepository<List<Site>>>()
    val selectedSite = MutableLiveData<ResRepository<String>>()

    companion object {

        private var mInstance: SiteController? = null

        fun getInstance(): SiteController {
            if (mInstance == null) {
                mInstance = SiteController()
            }
            return mInstance as SiteController
        }

    }

    fun getSelectedSite() {
        preferenceDataSourceL.getSelectedSite(object : LocalCallback<String> {
            override fun onResult(result: ResRepository<String>, response: Response<String>?) {
                val res = ResRepository<String>(result.origin)
                res.data = result.data
                selectedSite.value = res
            }
        })
    }

    fun saveSelectedSite(siteId: String) {
        preferenceDataSourceL.setSelectedSite(siteId, object : LocalCallback<String> {
            override fun onResult(result: ResRepository<String>, response: Response<String>?) {
                val res = ResRepository<String>(result.origin)
                res.data = result.data
                selectedSite.value = res
            }
        })
    }

    fun getSites() {
        sitesDataSourceL.getSites(object : LocalCallback<List<Site>> {
            override fun onResult(
                result: ResRepository<List<Site>>,
                response: Response<List<Site>>?
            ) {
                if (result.errorCode == RepoConst.ERROR_CODE_OK) {
                    sites.postValue(result)
                } else {
                    getSitesRemote()
                    Log.warn()
                }
            }
        })
    }

    private fun getSitesRemote() {
        sitesDataSourceR.getSites(object : RemoteCallback<List<RemoteSite>>() {
            override fun onResult(
                result: ResRepository<List<RemoteSite>>,
                response: Response<List<RemoteSite>>?
            ) {
                val res = ResRepository<List<Site>>(result.origin)
                if (result.errorCode == RepoConst.ERROR_CODE_OK) {
                    result.data?.let {
                        saveSite(it)
                        res.data = it.map { list -> list.toSite() }
                    }
                } else {
                    res.errorCode = result.errorCode
                    res.message = result.message
                    Log.warn(res.message)
                }
                sites.value = res
            }
        })
    }

    private fun saveSite(res: List<RemoteSite>) {
        val localsites = res.map { it.toSiteLocal() }
        localsites.let {
            sitesDataSourceL.createSites(localsites.toTypedArray(), object : LocalCallback<Unit> {
                override fun onResult(result: ResRepository<Unit>, response: Response<Unit>?) {

                }
            })
        }
    }
}




