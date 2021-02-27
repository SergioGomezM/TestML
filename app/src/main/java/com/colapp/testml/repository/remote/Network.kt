package com.colapp.testml.repository.remote

import com.colapp.testml.BuildConfig
import com.colapp.testml.repository.RepoConst.TIME_OUT
import com.colapp.testml.repository.remote.mercadolibre.ApiMercadoLibre
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Network private constructor() {

    companion object {

        private var apiMercadoLibre: ApiMercadoLibre? = null

        fun clientMercadoLibre(): ApiMercadoLibre {
            if (apiMercadoLibre == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val httpClient = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.URL_BASE_ML)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
                apiMercadoLibre = retrofit.create(ApiMercadoLibre::class.java)
            }
            return apiMercadoLibre as ApiMercadoLibre
        }

    }
}
