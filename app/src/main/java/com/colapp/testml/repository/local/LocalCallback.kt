package com.colapp.testml.repository.local

import com.colapp.testml.repository.ResRepository
import retrofit2.Response

interface LocalCallback<T> {
    fun onResult(result: ResRepository<T>, response: Response<T>?)
}