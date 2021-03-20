package com.example.github_client.navigation

import com.example.github_client.mvp.model.entity.GithubUser
import com.example.github_client.ui.fragments.UserFragment
import com.example.github_client.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen() : SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserScreen(private val user: GithubUser) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }
}