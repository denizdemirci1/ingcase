package com.deniz.ingcase.data.services

import com.deniz.ingcase.model.reponse.GithubRepo
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author: deniz.demirci
 * @date: 15.04.2021
 */

interface Service {

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @GET("users/{username}/repos")
    suspend fun fetchUserRepos(
        @Path("username") username: String,
    ): List<GithubRepo>

}