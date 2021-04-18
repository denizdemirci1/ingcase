package com.deniz.ingcase.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.deniz.ingcase.data.repositories.GithubRepoRepository
import com.deniz.ingcase.model.reponse.GithubRepo
import com.deniz.ingcase.model.util.Result
import com.deniz.ingcase.util.MainCoroutineRule
import com.deniz.ingcase.util.getOrAwaitValue
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author: deniz.demirci
 * @date: 18.04.2021
 */

@ExperimentalCoroutinesApi
class HomeViewModelTest {

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  @get:Rule
  var mainCoroutineRule = MainCoroutineRule()

  // Subject under test
  private lateinit var viewModel: HomeViewModel

  @MockK
  lateinit var repository: GithubRepoRepository

  @Before
  fun setUp() {
    MockKAnnotations.init(this, relaxed = true)
    viewModel = HomeViewModel(repository)
  }

  @Test
  fun getUserRepos_WhenError_ShouldFireErrorEvent() =
      mainCoroutineRule.runBlockingTest {
        val exception = Exception()
        val res = Result.Error(exception)

        coEvery { repository.fetchUserRepos(any()) } returns res

        viewModel.getUserRepos("")

        Truth.assertThat(viewModel.event.getOrAwaitValue().getContentIfNotHandled())
            .isInstanceOf(HomeViewEvent.OnError::class.java)
      }

  @Test
  fun getUserRepos_WhenSuccess_ShouldFireSuccessEvent() =
      mainCoroutineRule.runBlockingTest {
        val res = Result.Success(listOf<GithubRepo>())

        coEvery { repository.fetchUserRepos(any()) } returns res

        viewModel.getUserRepos("denizdemirci1")
        Truth.assertThat(viewModel.event.getOrAwaitValue().getContentIfNotHandled())
            .isInstanceOf(HomeViewEvent.OnSuccess::class.java)
      }
}