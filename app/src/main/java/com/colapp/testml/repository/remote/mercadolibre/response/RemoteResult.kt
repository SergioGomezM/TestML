package com.colapp.testml.repository.remote.mercadolibre.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteResult(
    @SerializedName("title")
    val title: String?,
    @SerializedName("nick_name")
    val nick_name: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("condition")
    val condition: String?,
    @SerializedName("thumbnail")
    val img: String?,
    @SerializedName("attributes")
    val attributes: List<RemoteAttribute>?
) : Parcelable