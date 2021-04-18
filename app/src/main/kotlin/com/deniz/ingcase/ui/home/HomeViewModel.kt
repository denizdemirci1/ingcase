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
import com.deniz.ingcase.util.Event
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

    private val _event = MutableLiveData<Event<HomeViewEvent>>()
    val event: LiveData<Event<HomeViewEvent>> = _event

    private fun sendEvent(event: HomeViewEvent) {
        _event.value = Event(event)
    }

    val isFavorite = MutableLiveData(false)

    fun getUserRepos(username: String) {
        viewModelScope.launch {
            sendEvent(HomeViewEvent.Loading)
            repository.fetchUserRepos(username).let { result ->
                when (result) {
                    is Result.Success -> sendEvent(HomeViewEvent.OnSuccess(result.data))
                    is Result.Error -> sendEvent(HomeViewEvent.OnError)
                }
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
