package com.colapp.testml.repository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.colapp.testml.repository.local.entity.LocalSite

@Dao
interface SiteDao {

    @Query("SELECT * FROM site")
    fun getSites(): Array<LocalSite>

    @Insert()
    fun createSites(site: Array<LocalSite>)


}