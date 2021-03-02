package com.colapp.testml.util

import com.colapp.testml.model.Alert
import com.colapp.testml.repository.ResRepository

class AlertUtil {

    companion object {

        fun createAlert(res: ResRepository<*>): Alert{
            Log.error(res.message)
            return Alert(
                    res.origin,
                    res.errorCode
            )
        }

    }

}