package com.colapp.testml.util

import android.content.res.Resources
import androidx.appcompat.app.AlertDialog
import com.colapp.testml.R
import com.colapp.testml.model.Alert
import com.colapp.testml.repository.RepoConst.LOCAL_ORIGIN
import com.colapp.testml.repository.RepoConst.REMOTE_ORIGIN

class AlertCreator (private val builder: AlertDialog.Builder, private val res: Resources, private val alert: Alert) {

       fun createAlert(): AlertDialog{
            return when (alert.source) {
                LOCAL_ORIGIN -> internetAlert()
                REMOTE_ORIGIN -> localAlert()
                else -> otherAlert()
            }
        }

        private fun internetAlert(): AlertDialog{
            builder.setTitle(res.getText(R.string.alert_title_internet))
            builder.setMessage(res.getText(R.string.alert_message_internet))
            return builder.create()
        }

        private fun localAlert(): AlertDialog{
            builder.setTitle(res.getText(R.string.alert_title_local))
            builder.setMessage(res.getText(R.string.alert_message_local))
            return builder.create()
        }

        private fun otherAlert(): AlertDialog{
            builder.setTitle(res.getText(R.string.alert_title_other))
            builder.setMessage(res.getText(R.string.alert_message_other))
            return builder.create()
        }

}

