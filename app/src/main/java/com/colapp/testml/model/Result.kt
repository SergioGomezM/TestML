package com.colapp.testml.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val title: String?,
    val nick_name: String?,
    val price: Int?,
    val condition: String?,
    val img: String?,
    val Attributes: List<Attribute>?
) : Parcelable
