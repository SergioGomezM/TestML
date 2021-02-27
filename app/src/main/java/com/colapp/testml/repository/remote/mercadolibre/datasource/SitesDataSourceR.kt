package com.colapp.testml.repository.remote.mercadolibre.datasource

import com.colapp.testml.repository.RepoConst.ERROR_EXCEPTION
import com.colapp.testml.repository.RepoConst.MESSAGE_EXCEPTION
import com.colapp.testml.repository.RepoConst.REMOTE_ORIGIN
import com.colapp.testml.repository.ResRepository
import com.colapp.testml.repository.remote.RemoteCallback
import com.colapp.testml.repository.remote.mercadolibre.ConsumerServicesML
import com.colapp.testml.repository.remote.mercadolibre.response.RemoteSite
import com.colapp.testml.util.Log

class SitesDataSourceR private constructor() {

    private val consumerServicesML = ConsumerServicesML.getInstance()

    companion object {

        private var mInstance: SitesDataSourceR? = null

        fun getInstance(): SitesDataSourceR {
            if (mInstance == null) {
                mInstance = SitesDataSourceR()
            }
            return mInstance as SitesDataSourceR
        }

    }

    fun getSites(callback: RemoteCallback<List<RemoteSite>>) {
        try {
            consumerServicesML.getSites(callback)
        } catch (ex: Exception) {
            Log.error(ex.message)
            val res = ResRepository<List<RemoteSite>>(REMOTE_ORIGIN)
            res.errorCode = ERROR_EXCEPTION
            res.message = MESSAGE_EXCEPTION
            callback.onResult(res, null)
        }
    }
}