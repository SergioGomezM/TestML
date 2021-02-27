package com.colapp.testml.repository.controller

import androidx.lifecycle.MutableLiveData
import com.colapp.testml.model.Query
import com.colapp.testml.repository.RepoConst
import com.colapp.testml.repository.remote.mercadolibre.datasource.QueryDataSourceR
import com.colapp.testml.repository.ResRepository
import com.colapp.testml.repository.controller.mapper.toQuery
import com.colapp.testml.repository.local.datasource.PreferencesDataSourceL
import com.colapp.testml.repository.remote.RemoteCallback
import com.colapp.testml.repository.remote.mercadolibre.response.RemoteQuery
import com.colapp.testml.util.Log
import retrofit2.Response

class QueryController {

    private val queryDataSource = QueryDataSourceR.getInstance()

    val query = MutableLiveData<ResRepository<Query>>()

    companion object {

        private var mInstance: QueryController? = null

        fun getInstance(): QueryController {
            if (mInstance == null) {
                mInstance = QueryController()
            }
            return mInstance as QueryController
        }
    }

    fun getQueryBySite(siteId: String, queryP: String) {
        queryDataSource.getQueryBySite(siteId, queryP, object : RemoteCallback<RemoteQuery>() {
            override fun onResult(result: ResRepository<RemoteQuery>, response: Response<RemoteQuery>?) {
                val res = ResRepository<Query>(result.origin)
                if(result.errorCode == RepoConst.ERROR_CODE_OK){
                    res.data = result.data?.toQuery()
                }else{
                    res.errorCode = result.errorCode
                    res.message = result.message
                    Log.warn(res.message)
                }
                query.value = res
            }
        })
    }

}