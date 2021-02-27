package com.colapp.testml.viewmodel.activity

import com.colapp.testml.util.Log
import com.colapp.testml.viewmodel.ViewModelPlus

class DetailActivityViewModel: ViewModelPlus() {

    override fun onStart() {
        Log.info()
        if (startVmPlus) {
            startVmPlus = false
        }
    }

    override fun onFinish() {
        Log.info()
    }

}