package com.colapp.testml.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Paging(
    val total: Int?,
    val offset: Int?,
    val limit: Int?,
    val count_results: Int?
) : Parcelable
