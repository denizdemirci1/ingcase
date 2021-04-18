package com.deniz.ingcase.di

import android.content.Context
import androidx.room.Room
import com.deniz.ingcase.data.local.AppDatabase
import com.deniz.ingcase.data.local.GithubRepoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author: deniz.demirci
 * @date: 18.04.2021
 */

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

  @Provides
  fun provideRepoDao(appDatabase: AppDatabase): GithubRepoDao {
    return appDatabase.githubRepoDao()
  }

  @Provides
  @Singleton
  fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
    return Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "RepoDatabase"
    ).build()
  }
}
