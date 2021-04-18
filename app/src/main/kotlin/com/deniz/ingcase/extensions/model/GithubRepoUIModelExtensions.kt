package com.deniz.ingcase.extensions.model

import com.deniz.ingcase.data.local.entities.LocalRepo
import com.deniz.ingcase.model.ui.GithubRepoUIModel

/**
 * @author: deniz.demirci
 * @date: 18.04.2021
 */

fun GithubRepoUIModel.toLocalRepo(isStarred: Boolean): LocalRepo {
  return LocalRepo(
      repoId = this.id,
      ownerId = this.owner.login,
      isStarred = isStarred
  )
}

fun List<GithubRepoUIModel>.toLocalRepoList(): List<LocalRepo> {
  val list = mutableListOf<LocalRepo>()
  list.addAll(
      this.map {
        LocalRepo(
            repoId = it.id,
            ownerId = it.owner.login,
            isStarred = false
        )
      }
  )
  return list
}
