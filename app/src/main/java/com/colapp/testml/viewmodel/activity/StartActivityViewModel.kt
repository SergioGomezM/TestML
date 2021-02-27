package com.colapp.testml.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.colapp.testml.model.Alert
import com.colapp.testml.model.Site
import com.colapp.testml.repository.RepoConst
import com.colapp.testml.repository.ResRepository
import com.colapp.testml.util.Log
import com.colapp.testml.viewmodel.ViewModelPlus

class StartActivityViewModel : ViewModelPlus() {

    private var siteSelectedObserver = createSiteSelectedObserver()
    private var sitesObserver = createSitesObserver()

    private var selectedSiteSkip = false

    val selectedSite = MutableLiveData<String>()
    val sites = MutableLiveData<List<Site>>()
    val alert = MutableLiveData<Alert>()

    override fun onStart() {
        Log.info()
        if (startVmPlus) {
            startVmPlus = false
            repositoryFacade.selectedSite.observeForever(siteSelectedObserver)
            repositoryFacade.sites.observeForever(sitesObserver)
            repositoryFacade.getSites()
        }
    }

    override fun onFinish() {
        Log.info()
        repositoryFacade.selectedSite.removeObserver(siteSelectedObserver)
        repositoryFacade.sites.removeObserver(sitesObserver)
    }

    private fun createSitesObserver(): Observer<ResRepository<List<Site>>> {
        return Observer<ResRepository<List<Site>>> {
            Log.info(it.message)
            if (it.errorCode == RepoConst.ERROR_CODE_OK) {
                sites.value = it.data
                selectedSiteSkip = true
            } else{
                createAlert(it)
            }
        }
    }

    private fun createSiteSelectedObserver(): Observer<ResRepository<String>> {
        siteSelectedObserver = Observer<ResRepository<String>> {
            if (it.data != null) {
                selectedSite.value = it.data
            }
        }
        return siteSelectedObserver
    }

    fun saveSelectedSite(idSite: String){
        if (!selectedSiteSkip && !selectedSite.value.equals(idSite)) {
            repositoryFacade.saveSelectedSite(idSite)
        } else {
            selectedSiteSkip = false
            repositoryFacade.getSelectedSite()
        }
    }

    fun getIdSelectItem (idSite: String): Int{
        Log.info(idSite)
        for ((index, value) in sites.value?.withIndex()!!) {
            if (value.id == idSite) {
                return index
            }
        }
        return 0
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