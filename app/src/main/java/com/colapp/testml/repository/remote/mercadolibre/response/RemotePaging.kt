package com.colapp.testml.repository.remote.mercadolibre.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemotePaging(
    @SerializedName("total")
    val total: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("primary_results")
    val count_results: Int?
) : Parcelable