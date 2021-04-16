package com.deniz.ingcase.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deniz.ingcase.model.reponse.GithubRepo
import com.deniz.ingcase.model.ui.GithubRepoUIModel
import com.deniz.ingcase.ui.base.BaseListAdapter

/**
 * @author: deniz.demirci
 * @date: 16.04.2021
 */

class RepoAdapter(
    private val onRepoClick: ((GithubRepoUIModel) -> Unit)? = null
) : BaseListAdapter<GithubRepoUIModel>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return RepoViewHolder(parent, inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { repo ->
            when (holder) {
                is RepoViewHolder -> holder.bind(
                    repo,
                    onRepoClick
                )
            }
        }
    }
}
