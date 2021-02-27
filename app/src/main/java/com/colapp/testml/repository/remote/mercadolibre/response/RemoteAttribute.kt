package com.colapp.testml.repository.remote.mercadolibre.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteAttribute(
    @SerializedName("value_name")
    val value: String?,
    @SerializedName("name")
    val name: String?
) : Parcelable