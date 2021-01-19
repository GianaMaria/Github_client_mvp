package com.example.github_client.mvp.presenter

import com.example.github_client.mvp.model.entity.GithubUser
import com.example.github_client.mvp.view.UserView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(private val user: GithubUser, private val router: Router) :
    MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setText(user.login)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}