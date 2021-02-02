package com.example.github_client.mvp.model.cache

import com.example.github_client.mvp.model.entity.GithubRepository
import com.example.github_client.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesCache {

    fun insert(user: GithubUser, repository: List<GithubRepository>): Completable

    fun update(user: GithubUser, repository: List<GithubRepository>): Completable

    fun delete(user: GithubUser, repository: List<GithubRepository>): Completable

    fun getUserRepos(user: GithubUser): Single<List<GithubRepository>>
}