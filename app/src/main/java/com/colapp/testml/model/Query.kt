package com.colapp.testml.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Query(
    val value: Paging?,
    val Results: List<Result>?
) : Parcelable


