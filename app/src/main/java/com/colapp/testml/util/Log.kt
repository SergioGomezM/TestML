package com.colapp.testml.util

import android.util.Log
import com.colapp.testml.BuildConfig

class Log {

    companion object {

        private const val DEFAULT = "default"
        private const val TAG = BuildConfig.ENVIRONMENT

        fun debug(msg: String? = DEFAULT) {
            Log.d(TAG, "${getFunName("debug")}->${msg}")
        }

        fun info(msg: String? = DEFAULT) {
            Log.i(TAG, "${getFunName("info")}->${msg}")
        }

        fun warn(msg: String? = DEFAULT) {
            Log.w(TAG, "${getFunName("warn")}->${msg}")
        }

        fun error(msg: String? = DEFAULT) {
            Log.e(TAG, "${getFunName("error")}->${msg}")
        }

        fun getFunName(function: String): String{
            var result = ""
            var nextLine = false
            Thread.currentThread().stackTrace.forEach{
                if(nextLine && !it.methodName.contains(DEFAULT)){
                    result = "${it.fileName}.${it.methodName}.line:${it.lineNumber}: "
                    nextLine = false
                }
                if(function.equals(it.methodName)){
                    nextLine = true
                }
            }
            return result
        }
    }
}