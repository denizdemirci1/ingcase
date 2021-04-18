package com.deniz.ingcase.di

import com.deniz.ingcase.data.local.GithubRepoDao
import com.deniz.ingcase.data.local.RepoLocalDataSource
import com.deniz.ingcase.data.local.RepoLocalDataSourceImpl
import com.deniz.ingcase.data.remote.datasources.GithubRepoDataSource
import com.deniz.ingcase.data.remote.datasources.GithubRepoDataSourceImpl
import com.deniz.ingcase.data.services.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author: deniz.demirci
 * @date: 15.04.2021
 */

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideGithubRepoDataSource(
        service: Service
    ): GithubRepoDataSource {
        return GithubRepoDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideRepoLocalDataSource(
        repoDao: GithubRepoDao
    ): RepoLocalDataSource {
        return RepoLocalDataSourceImpl(repoDao)
    }
}
