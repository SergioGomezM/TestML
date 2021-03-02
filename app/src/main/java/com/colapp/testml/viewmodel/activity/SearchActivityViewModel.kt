package com.colapp.testml.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.colapp.testml.model.Alert
import com.colapp.testml.model.Query
import com.colapp.testml.repository.RepoConst.ERROR_CODE_OK
import com.colapp.testml.repository.ResRepository
import com.colapp.testml.util.AlertUtil
import com.colapp.testml.util.Log
import com.colapp.testml.viewmodel.ViewModelPlus

class SearchActivityViewModel: ViewModelPlus() {

    private var siteSelectedObserver = siteSelectedObserver()
    private var queryObserver = queryObserver()

    private lateinit var siteSelect: String

    var isSearching = MutableLiveData<Boolean>()
    val query = MutableLiveData<Query>()
    val alert = MutableLiveData<Alert>()

    //region override
    override fun onStart() {
        Log.info()
        if (startVmPlus) {
            startVmPlus = false
            isSearching.value = false
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
    //endregion

    //region public function
    fun getQueryBySite(queryP: String){
        if (isSearching.value == false) {
            repositoryFacade.getQueryBySite(siteSelect, queryP)
        }
        isSearching.value = true
    }
    //endregion

    //region private function
    private fun siteSelectedObserver(): Observer<ResRepository<String>> {
        siteSelectedObserver = Observer<ResRepository<String>> {
            Log.info(it.message)
            siteSelect = it.data.toString()
            Log.debug(it.data.toString())
        }
        return siteSelectedObserver
    }

    private fun queryObserver(): Observer<ResRepository<Query>> {
        queryObserver = Observer<ResRepository<Query>> {
            Log.info(it.message)
            isSearching.value = false
            if(it.errorCode == ERROR_CODE_OK){
                query.value = it.data
            }else{
                alert.value = AlertUtil.createAlert(it)
            }
        }
        return queryObserver
    }
    //endregion
}