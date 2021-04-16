package com.deniz.ingcase.model.reponse

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * @author: deniz.demirci
 * @date: 16.04.2021
 */

@Parcelize
data class GithubRepo(
    val id: String,
    val name: String,
    val owner: Owner,
    @SerializedName("stargazers_count") val star: Int,
    @SerializedName("open_issues_count") val issues: Int
): Parcelable

@Parcelize
data class Owner(
    @SerializedName("avatar_url") val avatar: String,
    val login: String
): Parcelable