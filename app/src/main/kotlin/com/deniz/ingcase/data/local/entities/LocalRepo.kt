package com.deniz.ingcase.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author: deniz.demirci
 * @date: 18.04.2021
 */

@Entity(tableName = "repos")
data class LocalRepo(
    @PrimaryKey
    @ColumnInfo(name = "repoId") val repoId: String,
    @ColumnInfo(name = "ownerId") val ownerId: String,
    @ColumnInfo(name = "isStarred") val isStarred: Boolean = false,
)
