package com.deniz.ingcase.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.deniz.ingcase.databinding.ViewHolderRepoBinding
import com.deniz.ingcase.model.reponse.GithubRepo
import com.deniz.ingcase.model.ui.GithubRepoUIModel
import com.deniz.ingcase.ui.base.BaseViewHolder

/**
 * @author: deniz.demirci
 * @date: 16.04.2021
 */

class RepoViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : BaseViewHolder<ViewHolderRepoBinding>(
    binding = ViewHolderRepoBinding.inflate(inflater, parent, false)
) {

    fun bind(
        repo: GithubRepoUIModel,
        onProductClick: ((GithubRepoUIModel) -> Unit)?
    ) {
        binding.apply {
            repoName.text = repo.name
            favoriteIcon.visibility = if (repo.isFavorite) View.VISIBLE else View.GONE
            repoRoot.setOnClickListener { onProductClick?.invoke(repo) }
        }
    }
}