package com.colapp.testml.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.colapp.testml.model.Alert
import com.colapp.testml.model.Query
import com.colapp.testml.repository.RepoConst.ERROR_CODE_OK
import com.colapp.testml.repository.ResRepository
import com.colapp.testml.util.Log
import com.colapp.testml.viewmodel.ViewModelPlus

class SearchActivityViewModel: ViewModelPlus() {

    private var siteSelectedObserver = siteSelectedObserver()
    private var queryObserver = queryObserver()

    private lateinit var siteSelect: String

    private var isSearchin = false

    val query = MutableLiveData<Query>()
    val alert = MutableLiveData<Alert>()

    override fun onStart() {
        Log.info()
        if (startVmPlus) {
            startVmPlus = false
            repositoryFacade.selectedSite.observeForever(siteSelectedObserver())
            repositoryFacade.query.observeForever(queryObserver())
            repositoryFacade.getSelectedSite()
        }
    }

    override fun onFinish() {
        Log.info()
        repositoryFacade.selectedSite.removeObserver(siteSelectedObserver)
        repositoryFacade.query.removeObserver(queryObserver)
    }

    private fun siteSelectedObserver(): Observer<ResRepository<String>> {
        siteSelectedObserver = Observer<ResRepository<String>> {
            siteSelect = it.data.toString()
            Log.debug(it.data.toString())
        }
        return siteSelectedObserver
    }

    private fun queryObserver(): Observer<ResRepository<Query>> {
        queryObserver = Observer<ResRepository<Query>> {
            if(it.errorCode == ERROR_CODE_OK){
                query.value = it.data
            }else{
                createAlert(it)
            }
        }
        return queryObserver
    }

    fun getQueryBySite(queryP: String){
        isSearchin = true
        Log.info(siteSelect)
        repositoryFacade.getQueryBySite(siteSelect, queryP)
    }

    private fun createAlert(res: ResRepository<*>){
        Log.error(res.message)
        val newAlert = Alert(
            res.origin,
            res.errorCode
        )
        alert.value = newAlert
    }

}