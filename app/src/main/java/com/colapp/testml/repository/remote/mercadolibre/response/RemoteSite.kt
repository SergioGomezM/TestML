package com.colapp.testml.repository.remote.mercadolibre.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteSite(
    @SerializedName("default_currency_id")
    val currency_id: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
) : Parcelable
