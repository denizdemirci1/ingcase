package com.deniz.ingcase.di

import com.deniz.ingcase.data.local.RepoLocalDataSource
import com.deniz.ingcase.data.remote.datasources.GithubRepoDataSource
import com.deniz.ingcase.data.repositories.GithubRepoRepository
import com.deniz.ingcase.data.repositories.GithubRepoRepositoryImpl
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
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGithubRepoRepository(
        dataSource: GithubRepoDataSource,
        localDataSource: RepoLocalDataSource
    ): GithubRepoRepository {
        return GithubRepoRepositoryImpl(dataSource, localDataSource)
    }
}
