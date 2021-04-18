package com.deniz.ingcase.data.repositories

import com.deniz.ingcase.data.local.RepoLocalDataSource
import com.deniz.ingcase.data.local.entities.LocalRepo
import com.deniz.ingcase.data.remote.datasources.GithubRepoDataSource
import com.deniz.ingcase.model.reponse.GithubRepo
import com.deniz.ingcase.model.util.Result

/**
 * @author: deniz.demirci
 * @date: 15.04.2021
 */

interface GithubRepoRepository {

    suspend fun fetchUserRepos(username: String): Result<List<GithubRepo>>

    suspend fun starRepo(repo: LocalRepo)

    suspend fun unstarRepo(repo: LocalRepo)

    suspend fun getRepoById(repoId: String): LocalRepo?

    suspend fun insertAllReposToLocal(repos: List<LocalRepo>)
}

class GithubRepoRepositoryImpl(
    private val githubRepoDataSource: GithubRepoDataSource,
    private val repoLocalDataSource: RepoLocalDataSource
) : GithubRepoRepository {

    override suspend fun fetchUserRepos(username: String): Result<List<GithubRepo>> {
        return githubRepoDataSource.fetchUserRepos(username)
    }

    override suspend fun starRepo(repo: LocalRepo) {
        return repoLocalDataSource.starRepo(repo)
    }

    override suspend fun unstarRepo(repo: LocalRepo) {
        return repoLocalDataSource.unstarRepo(repo)
    }

    override suspend fun getRepoById(repoId: String): LocalRepo? {
        return repoLocalDataSource.getRepoById(repoId)
    }

    override suspend fun insertAllReposToLocal(repos: List<LocalRepo>) {
        repoLocalDataSource.insertAllRepos(repos)
    }
}
