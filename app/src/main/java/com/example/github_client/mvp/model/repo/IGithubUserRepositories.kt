package com.example.github_client.mvp.model.repo

import com.example.github_client.mvp.model.entity.GithubRepository
import io.reactivex.rxjava3.core.Single

interface IGithubUserRepositories {
    fun getRepositories(login: String): Single<List<GithubRepository>>
}