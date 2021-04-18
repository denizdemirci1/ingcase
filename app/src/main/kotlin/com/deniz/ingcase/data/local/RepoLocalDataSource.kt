package com.deniz.ingcase.data.local

import com.deniz.ingcase.data.local.entities.LocalRepo
import javax.inject.Inject

/**
 * @author: deniz.demirci
 * @date: 18.04.2021
 */

interface RepoLocalDataSource {

  suspend fun starRepo(repo: LocalRepo)

  suspend fun unstarRepo(repo: LocalRepo)

  suspend fun getRepoById(repoId: String): LocalRepo?

  suspend fun insertAllRepos(repos: List<LocalRepo>)
}

class RepoLocalDataSourceImpl @Inject constructor(
    private val repoDao: GithubRepoDao
) : RepoLocalDataSource {

  override suspend fun starRepo(repo: LocalRepo) {
    repoDao.updateStarred(repo.repoId, true)
  }

  override suspend fun unstarRepo(repo: LocalRepo) {
    repoDao.updateStarred(repo.repoId, false)
  }

  override suspend fun getRepoById(repoId: String): LocalRepo? {
    return repoDao.getRepoById(repoId)
  }

  override suspend fun insertAllRepos(repos: List<LocalRepo>) {
    repoDao.insertAllRepos(repos)
  }
}
