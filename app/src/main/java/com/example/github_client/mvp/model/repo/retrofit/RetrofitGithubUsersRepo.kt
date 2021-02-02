package com.example.github_client.mvp.model.repo.retrofit

import com.example.github_client.mvp.model.api.IDataSource
import com.example.github_client.mvp.model.cache.IGithubUsersCache
import com.example.github_client.mvp.model.entity.GithubUser
import com.example.github_client.mvp.model.entity.network.INetworkStatus
import com.example.github_client.mvp.model.repo.IGithubUsersRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val userCache: IGithubUsersCache
) : IGithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers().flatMap { users ->
                userCache.insert(users).toSingleDefault(users)
            }
        } else {
            userCache.getAll()
        }
    }.subscribeOn(Schedulers.io())

    override fun getUserData(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUserData(user.login).flatMap { user ->
                    userCache.insert(user).toSingleDefault(user)
                }
            } else {
                userCache.getUser(user)
            }
        }.subscribeOn(Schedulers.io())
}
