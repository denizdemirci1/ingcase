package com.deniz.ingcase.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deniz.ingcase.data.local.entities.LocalRepo
import com.deniz.ingcase.data.repositories.GithubRepoRepository
import com.deniz.ingcase.extensions.model.toLocalRepo
import com.deniz.ingcase.model.ui.GithubRepoUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author: deniz.demirci
 * @date: 16.04.2021
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: GithubRepoRepository
): ViewModel() {

    private val _localRepo = MutableLiveData<LocalRepo>()
    val localRepo: LiveData<LocalRepo> = _localRepo

    fun getLocalRepoById(repoId: String) {
        viewModelScope.launch {
            repository.getRepoById(repoId)?.let {
                _localRepo.value = it
            }
        }
    }

    fun starRepository(repo: GithubRepoUIModel) {
        viewModelScope.launch {
            repository.starRepo(repo.toLocalRepo(true))
            getLocalRepoById(repo.id)
        }
    }

    fun unstarRepository(repo: GithubRepoUIModel) {
        viewModelScope.launch {
            repository.unstarRepo(repo.toLocalRepo(false))
            getLocalRepoById(repo.id)
        }
    }
}
