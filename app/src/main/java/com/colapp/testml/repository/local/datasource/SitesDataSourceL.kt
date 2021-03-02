package com.colapp.testml.repository.local.datasource

import com.colapp.testml.model.Site
import com.colapp.testml.repository.RepoConst
import com.colapp.testml.repository.local.LocalCallback
import com.colapp.testml.repository.RepoConst.ERROR_DATA_NOT_FOUND
import com.colapp.testml.repository.RepoConst.LOCAL_ORIGIN
import com.colapp.testml.repository.RepoConst.MESSAGE_DATA_NOT_FOUND
import com.colapp.testml.repository.ResRepository
import com.colapp.testml.repository.controller.mapper.toSite
import com.colapp.testml.repository.local.DataBase
import com.colapp.testml.repository.local.TestMLdb
import com.colapp.testml.repository.local.entity.LocalSite
import com.colapp.testml.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SitesDataSourceL private constructor() {

    private val dataBase: TestMLdb = DataBase.getInstance().testMLdb

    companion object {

        private var mInstance: SitesDataSourceL? = null

        fun getInstance(): SitesDataSourceL {
            if (mInstance == null) {
                mInstance = SitesDataSourceL()
            }
            return mInstance as SitesDataSourceL
        }

    }

    fun getSites(callback: LocalCallback<List<Site>>) {
        GlobalScope.launch(Dispatchers.IO) {
            val res = ResRepository<List<Site>>(LOCAL_ORIGIN)
            try {
                val resultDB = dataBase.siteDao().getSites().map { it.toSite() }
                if (resultDB.isEmpty()){
                    res.errorCode = ERROR_DATA_NOT_FOUND
                    res.message = MESSAGE_DATA_NOT_FOUND
                }else{
                    res.data = resultDB
                }
            }catch (ex: Exception){
                Log.error(ex.message)
                res.errorCode = RepoConst.ERROR_EXCEPTION
                res.message = RepoConst.MESSAGE_EXCEPTION
            }
            callback.onResult(res, null)
        }
    }

    fun createSites(sites: Array<LocalSite>, callback: LocalCallback<Unit>) {
        GlobalScope.launch(Dispatchers.IO) {
            val res = ResRepository<Unit>(LOCAL_ORIGIN)
            try {
                res.data = dataBase.siteDao().createSites(sites)
            } catch (ex: Exception){
                Log.error(ex.message)
                res.errorCode = RepoConst.ERROR_EXCEPTION
                res.message = RepoConst.MESSAGE_EXCEPTION
            }
            callback.onResult(res, null)
        }
    }
}

