package com.deniz.ingcase.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deniz.ingcase.data.local.entities.LocalRepo

/**
 * @author: deniz.demirci
 * @date: 18.04.2021
 */

@Dao
interface GithubRepoDao {

  @Query("SELECT * FROM repos")
  fun getAllLocalRepos(): List<LocalRepo>

  @Query("SELECT * FROM repos WHERE ownerId IN (:ownerId)")
  fun getAllLocalReposByOwnerId(ownerId: String): List<LocalRepo>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertRepo(repo: LocalRepo)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAllRepos(repos: List<LocalRepo>)

  @Query("DELETE FROM repos WHERE repoId = :repoId")
  suspend fun deleteRepoById(repoId: String): Int

  @Query("UPDATE repos SET isStarred = :isStarred WHERE repoId = :repoId")
  suspend fun updateStarred(repoId: String, isStarred: Boolean)

  @Query("SELECT * FROM repos WHERE repoId IN (:repoId)")
  suspend fun getRepoById(repoId: String): LocalRepo?
}
