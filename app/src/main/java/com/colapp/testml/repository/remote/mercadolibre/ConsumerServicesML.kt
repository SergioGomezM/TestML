package com.colapp.testml.repository.remote.mercadolibre

import com.colapp.testml.repository.remote.Network
import com.colapp.testml.repository.remote.mercadolibre.response.RemoteQuery
import com.colapp.testml.repository.remote.mercadolibre.response.RemoteSite
import retrofit2.Callback

class ConsumerServicesML {

    private val clientML = Network.clientMercadoLibre()

    private constructor()

    companion object {

        private var consumerServicesML: ConsumerServicesML? = null

        fun getInstance(): ConsumerServicesML {
            if (consumerServicesML == null) {
                consumerServicesML = ConsumerServicesML()
            }
            return consumerServicesML as ConsumerServicesML
        }

    }

    fun getSites(cb: Callback<List<RemoteSite>>) {
        clientML.sites().enqueue(cb)
    }

    fun getQueryBySite(site: String, query: String, cb: Callback<RemoteQuery>) {
        clientML.queryBySite(site, query).enqueue(cb)
    }

}