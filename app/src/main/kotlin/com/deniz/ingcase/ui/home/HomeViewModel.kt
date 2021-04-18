package com.deniz.ingcase.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deniz.ingcase.data.local.entities.LocalRepo
import com.deniz.ingcase.data.repositories.GithubRepoRepository
import com.deniz.ingcase.extensions.model.toLocalRepoList
import com.deniz.ingcase.model.reponse.GithubRepo
import com.deniz.ingcase.model.ui.GithubRepoUIModel
import com.deniz.ingcase.model.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author: deniz.demirci
 * @date: 16.04.2021
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GithubRepoRepository
): ViewModel() {

    private val _repoLiveData = MutableLiveData<Result<List<GithubRepo>>>()
    val repoLiveData: LiveData<Result<List<GithubRepo>>> = _repoLiveData

    val isFavorite = MutableLiveData(false)

    fun getUserRepos(username: String) {
        viewModelScope.launch {
            _repoLiveData.postValue(Result.Loading)
            repository.fetchUserRepos(username).let { result ->
                _repoLiveData.postValue(result)
            }
        }
    }

    fun saveReposToLocal(repos: List<GithubRepoUIModel>) {
        viewModelScope.launch {
            repository.insertAllReposToLocal(repos.toLocalRepoList())
        }
    }

    fun getRepoById(repoId: String) {
        viewModelScope.launch {
            repository.getRepoById(repoId)?.let {
                isFavorite.value = it.isStarred
            }
        }
    }
}
