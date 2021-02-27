package com.colapp.testml.repository.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "site")
data class LocalSite(
    @PrimaryKey(autoGenerate = true)
    val id_db: Int,
    @ColumnInfo(name = "currency_id")
    val currency_id: String?,
    @ColumnInfo(name = "id")
    val id: String?,
    @ColumnInfo(name = "name")
    val name: String?
)

