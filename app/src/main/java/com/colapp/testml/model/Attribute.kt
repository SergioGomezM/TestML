package com.colapp.testml.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Attribute(
    val value: String?,
    val name: String?
) : Parcelable
