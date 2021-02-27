package com.colapp.testml.repository.remote.mercadolibre.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteQuery(
    @SerializedName("paging")
    val paging: RemotePaging?,
    @SerializedName("results")
    val results: List<RemoteResult>?
) : Parcelable


