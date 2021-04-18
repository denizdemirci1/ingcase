package com.deniz.ingcase.ui.detail

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.deniz.ingcase.R
import com.deniz.ingcase.databinding.FragmentDetailBinding
import com.deniz.ingcase.model.ui.GithubRepoUIModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author: deniz.demirci
 * @date: 15.04.2021
 */

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val args: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModels()

    private var isRepoStarred = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(args.repo)
        setObservers()
        setListeners()
    }

    private fun initViews(repo: GithubRepoUIModel) {
        viewModel.getLocalRepoById(repo.id)
        binding.apply {
            Glide.with(avatar).load(repo.owner.avatar).into(avatar)
            tvTitle.text = repo.name
            tvOwnerName.text = repo.owner.login
            tvStar.text = getString(R.string.star_text, repo.star)
            tvIssues.text = getString(R.string.issue_text, repo.issues)
        }
    }

    private fun setListeners() {
        binding.apply {
            ivBack.setOnClickListener { findNavController().popBackStack() }
            buttonStar.setOnClickListener { onStarClicked() }
        }
    }

    private fun setObservers() {
        viewModel.localRepo.observe(viewLifecycleOwner, { localRepo ->
            if (localRepo.isStarred) {
                isRepoStarred = true
                binding.buttonStar.setImageResource(R.drawable.ic_star_white_fill_24)
            } else {
                isRepoStarred = false
                binding.buttonStar.setImageResource(R.drawable.ic_star_white_empty_24)
            }
        })
    }

    private fun onStarClicked() {
        if (isRepoStarred) {
            viewModel.unstarRepository(args.repo)
        } else {
            viewModel.starRepository(args.repo)
        }
    }
}
