package com.deniz.ingcase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.deniz.ingcase.data.local.entities.LocalRepo

/**
 * @author: deniz.demirci
 * @date: 18.04.2021
 */

@Database(entities = [LocalRepo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun githubRepoDao(): GithubRepoDao
}
