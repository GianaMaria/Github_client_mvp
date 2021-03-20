package com.example.github_client.mvp.presenter

import com.example.github_client.mvp.view.MainView
import com.example.github_client.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClicked() {
        router.exit()
    }

}