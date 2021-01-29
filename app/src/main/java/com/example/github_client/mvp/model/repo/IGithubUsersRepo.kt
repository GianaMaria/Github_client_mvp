package com.example.github_client.mvp.model.repo

import com.example.github_client.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserData(login: String): Single<GithubUser>
}