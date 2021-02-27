package com.colapp.testml.viewmodel

import androidx.lifecycle.ViewModel
import com.colapp.testml.repository.RepositoryFacade
import com.colapp.testml.util.Log

abstract class ViewModelPlus(): ViewModel() {

    var startVmPlus = true

    protected val repositoryFacade = RepositoryFacade.getInstance()

    abstract fun onStart()

    abstract fun onFinish()

    override fun onCleared() {
        onFinish()
        Log.info()
        super.onCleared()
    }
}