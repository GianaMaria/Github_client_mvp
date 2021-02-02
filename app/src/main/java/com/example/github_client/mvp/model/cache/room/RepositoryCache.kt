package com.example.github_client.mvp.model.cache.room

import com.example.github_client.mvp.model.cache.IGithubRepositoriesCache
import com.example.github_client.mvp.model.entity.GithubRepository
import com.example.github_client.mvp.model.entity.GithubUser
import com.example.github_client.mvp.model.entity.room.Database
import com.example.github_client.mvp.model.entity.room.RoomGithubRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RepositoryCache(private val db: Database) : IGithubRepositoriesCache {
    override fun insert(user: GithubUser, repository: List<GithubRepository>): Completable =
        Completable.fromAction {
            val roomUser = db.userDao.findByLogin(user.login)
            val roomRepo = repository.map {
                RoomGithubRepository(
                    it.id ?: "",
                    it.name ?: "",
                    it.forks ?: 0,
                    roomUser.id
                )
            }
            db.repositoryDao.insert(roomRepo)
        }.subscribeOn(Schedulers.io())

    override fun update(user: GithubUser, repository: List<GithubRepository>): Completable =
        Completable.fromAction {
            val roomUser = db.userDao.findByLogin(user.login)
            db.repositoryDao.update(repository.map {
                RoomGithubRepository(
                    it.id ?: "",
                    it.name ?: "",
                    it.forks ?: 0,
                    roomUser.id
                )
            })
        }.subscribeOn(Schedulers.io())

    override fun delete(user: GithubUser, repository: List<GithubRepository>): Completable =
        Completable.fromAction {
            val roomUser = db.userDao.findByLogin(user.login)
            db.repositoryDao.delete(repository.map {
                RoomGithubRepository(
                    it.id ?: "",
                    it.name ?: "",
                    it.forks ?: 0,
                    roomUser.id
                )
            })
        }.subscribeOn(Schedulers.io())

    override fun getUserRepos(user: GithubUser): Single<List<GithubRepository>> =
        Single.fromCallable {
            val roomUser = db.userDao.findByLogin(user.login)
            return@fromCallable db.repositoryDao.findForUser(roomUser.id)
                .map { GithubRepository(it.id, it.name, it.forksCount) }
        }.subscribeOn(Schedulers.io())
}