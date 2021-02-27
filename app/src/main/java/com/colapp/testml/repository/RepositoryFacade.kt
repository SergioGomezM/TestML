package com.colapp.testml.repository

import com.colapp.testml.repository.controller.QueryController
import com.colapp.testml.repository.controller.SiteController
import com.colapp.testml.util.Log

class RepositoryFacade private constructor() {

    private val siteController = SiteController.getInstance()
    private val queryController = QueryController.getInstance()

    val selectedSite = siteController.selectedSite
    val sites = siteController.sites
    val query = queryController.query

    companion object {

        private var mIntance: RepositoryFacade? = null

        fun getInstance(): RepositoryFacade {
            if (mIntance == null) {
                mIntance = RepositoryFacade()
            }
            return mIntance as RepositoryFacade
        }
    }

    fun getSites() {
        siteController.getSites()
        Log.info()
    }

    fun getQueryBySite(siteId: String, queryP: String) {
        queryController.getQueryBySite(siteId, queryP)
        Log.info(siteId)
    }

    fun saveSelectedSite(siteId: String) {
        siteController.saveSelectedSite(siteId)
        Log.info(siteId)
    }

    fun getSelectedSite() {
        siteController.getSelectedSite()
        Log.info()
    }
}