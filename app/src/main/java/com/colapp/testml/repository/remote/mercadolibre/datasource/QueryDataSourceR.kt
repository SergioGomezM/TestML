package com.colapp.testml.repository.remote.mercadolibre.datasource

import com.colapp.testml.repository.RepoConst.ERROR_EXCEPTION
import com.colapp.testml.repository.RepoConst.MESSAGE_EXCEPTION
import com.colapp.testml.repository.RepoConst.REMOTE_ORIGIN
import com.colapp.testml.repository.ResRepository
import com.colapp.testml.repository.remote.RemoteCallback
import com.colapp.testml.repository.remote.mercadolibre.ConsumerServicesML
import com.colapp.testml.repository.remote.mercadolibre.response.RemoteQuery
import com.colapp.testml.util.Log

class QueryDataSourceR private constructor() {

    private val consumerServicesML = ConsumerServicesML.getInstance()

    companion object {

        private var mInstance: QueryDataSourceR? = null

        fun getInstance(): QueryDataSourceR {
            if (mInstance == null) {
                mInstance = QueryDataSourceR()
            }
            return mInstance as QueryDataSourceR
        }

    }

    fun getQueryBySite(site: String, query: String, callback: RemoteCallback<RemoteQuery>) {
        try {
            consumerServicesML.getQueryBySite(site, query, callback)
        } catch (ex: Exception) {
            Log.error(ex.message)
            val res = ResRepository<RemoteQuery>(REMOTE_ORIGIN)
            res.errorCode = ERROR_EXCEPTION
            res.message = MESSAGE_EXCEPTION
            callback.onResult(res, null)
        }
    }
}
