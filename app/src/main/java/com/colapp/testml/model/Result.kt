package com.colapp.testml.model

data class Result(
    val title: String?,
    val nick_name: String?,
    val price: Int?,
    val condition: String?,
    val img: String?,
    val Attributes: List<Attribute>?
)
