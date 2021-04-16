package com.deniz.ingcase.model.ui

import android.os.Parcelable
import com.deniz.ingcase.model.reponse.Owner
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * @author: deniz.demirci
 * @date: 16.04.2021
 */

@Parcelize
data class GithubRepoUIModel(
    val id: String,
    val name: String,
    val owner: Owner,
    @SerializedName("stargazers_count")
    val star: Int,
    @SerializedName("open_issues_count") val issues: Int,
    val isFavorite: Boolean = false
): Parcelable