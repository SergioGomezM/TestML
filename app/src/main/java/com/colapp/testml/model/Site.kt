package com.colapp.testml.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Site(
    val currency_id: String?,
    val id: String?,
    val name: String?
) : Parcelable
