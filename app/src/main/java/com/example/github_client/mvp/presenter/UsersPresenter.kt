package com.example.github_client.mvp.presenter

import com.example.github_client.mvp.model.entity.GithubUser
import com.example.github_client.mvp.model.entity.GithubUsersRepository
import com.example.github_client.mvp.presenter.list.IUserListPresenter
import com.example.github_client.mvp.view.UsersView
import com.example.github_client.mvp.view.list.UserItemView
import com.example.github_client.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(private val userRepository: GithubUsersRepository, private val router: Router) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.UserScreen(userRepository.getUsers()[itemView.pos]))
        }
    }

    private fun loadData() {
        val users = userRepository.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}