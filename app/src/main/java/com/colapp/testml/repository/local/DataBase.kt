package com.colapp.testml.repository.local

import android.content.Context
import androidx.room.Room
import com.colapp.testml.BuildConfig

class DataBase private constructor(context: Context) {

    var testMLdb = Room.databaseBuilder(
        context.applicationContext,
        TestMLdb::class.java,
        BuildConfig.DATA_BASE_NAME
    ).build()

    companion object {

        private var mInstance: DataBase? = null

        fun initializer(context: Context): DataBase {
            if (mInstance == null) {
                mInstance = DataBase(context)
            }
            return mInstance as DataBase
        }

        fun getInstance(): DataBase {
            return mInstance as DataBase
        }

    }

}