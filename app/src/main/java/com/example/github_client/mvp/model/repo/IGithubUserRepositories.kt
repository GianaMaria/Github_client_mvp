package com.example.github_client.mvp.model.repo

import com.example.github_client.mvp.model.entity.GithubRepository
import com.example.github_client.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUserRepositories {
    fun getRepositories(user: GithubUser): Single<List<GithubRepository>>
}