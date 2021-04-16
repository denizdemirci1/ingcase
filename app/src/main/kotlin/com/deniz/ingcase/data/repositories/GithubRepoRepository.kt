package com.deniz.ingcase.data.repositories

import com.deniz.ingcase.data.remote.datasources.GithubRepoDataSource
import com.deniz.ingcase.model.reponse.GithubRepo
import com.deniz.ingcase.model.util.Result

/**
 * @author: deniz.demirci
 * @date: 15.04.2021
 */

interface GithubRepoRepository {

    suspend fun fetchUserRepos(username: String): Result<List<GithubRepo>>
}

class GithubRepoRepositoryImpl(
    private val githubRepoDataSource: GithubRepoDataSource
) : GithubRepoRepository {

    override suspend fun fetchUserRepos(username: String): Result<List<GithubRepo>> {
        return githubRepoDataSource.fetchUserRepos(username)
    }
}
