package com.deniz.ingcase.ui.home

import com.deniz.ingcase.model.reponse.GithubRepo

/**
 * @author: deniz.demirci
 * @date: 18.04.2021
 */

sealed class HomeViewEvent {

  object Loading: HomeViewEvent()

  data class OnSuccess(val repos: List<GithubRepo>): HomeViewEvent()

  object OnError: HomeViewEvent()
}
