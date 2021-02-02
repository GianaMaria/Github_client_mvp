package com.example.github_client.mvp.presenter

import com.example.github_client.mvp.model.entity.GithubUser
import com.example.github_client.mvp.model.repo.IGithubUsersRepo
import com.example.github_client.mvp.presenter.list.IUserListPresenter
import com.example.github_client.mvp.view.list.UserItemView
import com.example.github_client.mvp.view.list.UsersView
import com.example.github_client.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(
    private val mainThreadScheduler: Scheduler,
    private val usersRepository: IGithubUsersRepo,
    private val router: Router
) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            usersRepository
                .getUserData(usersListPresenter.users[itemView.pos])
                .observeOn(mainThreadScheduler)
                .subscribe({
                    router.navigateTo(Screens.UserScreen(it))
                }, {
                    println("Error: ${it.message}")
                })
        }
    }

    private fun loadData() {
        usersRepository.getUsers()
            .observeOn(mainThreadScheduler)
            .subscribe({ users ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}