package com.colapp.testml.repository.remote.mercadolibre


import com.colapp.testml.repository.remote.mercadolibre.response.RemoteQuery
import com.colapp.testml.repository.remote.mercadolibre.response.RemoteSite
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiMercadoLibre {
    @GET("/sites")
    fun sites(): Call<List<RemoteSite>>

    @GET("/sites/{SITE_ID}/search")
    fun queryBySite(@Path("SITE_ID") site: String, @Query("q") q: String): Call<RemoteQuery>
}

