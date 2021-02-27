package com.colapp.testml.repository.remote

import com.colapp.testml.repository.RepoConst.ERROR_REQUEST
import com.colapp.testml.repository.RepoConst.REMOTE_ORIGIN
import com.colapp.testml.repository.ResRepository
import com.colapp.testml.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class RemoteCallback<T> : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        val res = ResRepository<T>(REMOTE_ORIGIN)
        if (response.isSuccessful) {
            res.data = response.body()
        } else {
            res.errorCode = response.code()
            res.message = response.message()
            Log.warn(res.message)
        }
        onResult(res, response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        val res = ResRepository<T>(REMOTE_ORIGIN)
        res.errorCode = ERROR_REQUEST
        res.message = t.message.toString()
        onResult(res, null)
        Log.error(t.message)
    }

    abstract fun onResult(result: ResRepository<T>, response: Response<T>?)
}