package com.example.github_client.mvp.presenter

import com.example.github_client.mvp.model.entity.GithubRepository
import com.example.github_client.mvp.view.RepositoryView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class RepositoryPresenter(private val userRepository: GithubRepository, private val router: Router) :
    MvpPresenter<RepositoryView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun getForksCount() = userRepository.forks

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}