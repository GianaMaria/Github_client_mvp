package com.example.github_client.mvp.model.repo.retrofit

import com.example.github_client.mvp.model.api.IDataSource
import com.example.github_client.mvp.model.cache.IGithubRepositoriesCache
import com.example.github_client.mvp.model.entity.GithubRepository
import com.example.github_client.mvp.model.entity.GithubUser
import com.example.github_client.mvp.model.entity.network.INetworkStatus
import com.example.github_client.mvp.model.repo.IGithubUserRepositories
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoryRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val repoCache: IGithubRepositoriesCache
) : IGithubUserRepositories {
    override fun getRepositories(user: GithubUser): Single<List<GithubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.login.let { login ->
                    api.getRepositories(login).flatMap { repositories ->
                        repoCache.insert(user, repositories).toSingleDefault(repositories)
                    }
                } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url"))
                    .subscribeOn(Schedulers.io())
            } else {
                repoCache.getUserRepos(user)
            }
        }.subscribeOn(Schedulers.io())
}