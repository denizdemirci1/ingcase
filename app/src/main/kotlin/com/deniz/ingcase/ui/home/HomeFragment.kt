package com.deniz.ingcase.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.deniz.ingcase.R
import com.deniz.ingcase.databinding.FragmentHomeBinding
import com.deniz.ingcase.model.reponse.GithubRepo
import com.deniz.ingcase.model.ui.GithubRepoUIModel
import com.deniz.ingcase.model.util.Result
import com.deniz.ingcase.ui.home.adapter.RepoAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author: deniz.demirci
 * @date: 15.04.2021
 */

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private var adapter: RepoAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setObservers()
        setListeners()
    }

    private fun setObservers() {
        viewModel.repoLiveData.observe(viewLifecycleOwner, { result ->
            when (result) {
                Result.Loading -> onLoading(true)
                is Result.Success -> {
                    onLoading(false)
                    onDataReceived(result.data.map {
                        viewModel.getRepoById(it.id)
                        GithubRepoUIModel(
                            it.id,
                            it.name,
                            it.owner,
                            it.star,
                            it.issues,
                            viewModel.isFavorite.value ?: false
                        )
                    })
                }
                is Result.Error -> {
                    onLoading(false)
                    onError()
                }
            }
        })
    }

    private fun setupAdapter() {
        adapter = RepoAdapter(::onRepoClick)
        binding.rvRepos.adapter = adapter
    }

    private fun setListeners() {
        binding.button.setOnClickListener {
            onSubmitClicked(binding.textInputEditText.text.toString())
        }
    }

    private fun onSubmitClicked(username: String) {
        viewModel.getUserRepos(username)
    }

    private fun onRepoClick(repo: GithubRepoUIModel) {
        val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(repo)
        findNavController().navigate(direction)
    }

    private fun onDataReceived(repos: List<GithubRepoUIModel>) {
        viewModel.saveReposToLocal(repos)
        adapter?.submitList(repos)
    }

    private fun onLoading(show: Boolean) {
        binding.progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun onError() {
        Toast.makeText(
            requireActivity(),
            getString(R.string.error_info),
            Toast.LENGTH_SHORT
        ).show()
    }
}
