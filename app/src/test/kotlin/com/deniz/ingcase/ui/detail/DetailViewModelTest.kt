package com.deniz.ingcase.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.deniz.ingcase.data.repositories.GithubRepoRepository
import com.deniz.ingcase.extensions.model.toLocalRepo
import com.deniz.ingcase.model.reponse.Owner
import com.deniz.ingcase.model.ui.GithubRepoUIModel
import com.deniz.ingcase.util.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
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
class DetailViewModelTest {

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  @get:Rule
  var mainCoroutineRule = MainCoroutineRule()

  // Subject under test
  private lateinit var viewModel: DetailViewModel

  @MockK
  lateinit var repository: GithubRepoRepository

  @Before
  fun setUp() {
    MockKAnnotations.init(this, relaxed = true)
    viewModel = DetailViewModel(repository)
  }

  @Test
  fun whenStarRepositoryCalled_VerifyStarRepoCall_VerifyLocalRepoCall() =
      mainCoroutineRule.runBlockingTest {
        val owner = Owner("", "")
        val repo = GithubRepoUIModel(
            id = "dumb_id",
            name = "dumb_name",
            owner = owner,
            star = 5,
            issues = 5,
            isFavorite = false
        )

        viewModel.starRepository(repo)

        coVerify { repository.starRepo(repo.toLocalRepo(true)) }
        coVerify { repository.getRepoById(repo.id) }
      }
}