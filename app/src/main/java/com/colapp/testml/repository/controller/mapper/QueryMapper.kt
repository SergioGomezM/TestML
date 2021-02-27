package com.colapp.testml.repository.controller.mapper

import com.colapp.testml.model.Attribute
import com.colapp.testml.model.Query
import com.colapp.testml.model.Paging
import com.colapp.testml.model.Result
import com.colapp.testml.repository.remote.mercadolibre.response.RemoteAttribute
import com.colapp.testml.repository.remote.mercadolibre.response.RemoteQuery
import com.colapp.testml.repository.remote.mercadolibre.response.RemotePaging
import com.colapp.testml.repository.remote.mercadolibre.response.RemoteResult

fun RemoteQuery.toQuery() = Query(
    paging?.toPaging(),
    results?.map {
        it.toResult()
    }
)

fun RemotePaging.toPaging() = Paging(
    total,
    offset,
    limit,
    count_results
)

fun RemoteResult.toResult() = Result(
    title,
    nick_name,
    price,
    condition,
    img,
    attributes?.map {
        it.toAttribute()
    }
)

fun RemoteAttribute.toAttribute() = Attribute(
    value,
    name
)