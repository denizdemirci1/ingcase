package com.deniz.ingcase.data.remote.datasources

import com.deniz.ingcase.data.services.Service
import com.deniz.ingcase.model.reponse.GithubRepo
import com.deniz.ingcase.model.util.Result
import javax.inject.Inject

/**
 * @author: deniz.demirci
 * @date: 15.04.2021
 */

interface GithubRepoDataSource {

    suspend fun fetchUserRepos(username: String): Result<List<GithubRepo>>
}

class GithubRepoDataSourceImpl @Inject constructor(
    private val service: Service
) : GithubRepoDataSource {

    override suspend fun fetchUserRepos(username: String): Result<List<GithubRepo>> {
        return try {
            val topArtist = service.fetchUserRepos(username)
            Result.Success(topArtist)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
