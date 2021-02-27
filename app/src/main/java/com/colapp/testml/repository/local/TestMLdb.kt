package com.colapp.testml.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.colapp.testml.repository.local.dao.SiteDao
import com.colapp.testml.repository.local.entity.LocalSite

@Database(
    entities = [
        LocalSite::class
    ], version = 1
)
abstract class TestMLdb : RoomDatabase() {
    abstract fun siteDao(): SiteDao
}


