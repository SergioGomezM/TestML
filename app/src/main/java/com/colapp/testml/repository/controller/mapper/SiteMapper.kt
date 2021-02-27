package com.colapp.testml.repository.controller.mapper

import com.colapp.testml.model.Site
import com.colapp.testml.repository.local.entity.LocalSite
import com.colapp.testml.repository.remote.mercadolibre.response.RemoteSite

fun RemoteSite.toSite() = Site(
    currency_id,
    id,
    name
)

fun RemoteSite.toSiteLocal() = LocalSite(
    0,
    currency_id,
    id,
    name
)

fun LocalSite.toSite() = Site(
    currency_id,
    id,
    name
)