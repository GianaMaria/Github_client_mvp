package com.example.github_client.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.github_client.App
import com.example.github_client.R
import com.example.github_client.mvp.model.entity.GithubUser
import com.example.github_client.mvp.presenter.UserPresenter
import com.example.github_client.mvp.view.UserView
import com.example.github_client.ui.BackButtonListener
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment(private val user: GithubUser) : MvpAppCompatFragment(), UserView,
    BackButtonListener {

    companion object {
        fun newInstance(user: GithubUser) = UserFragment(user)
    }

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            user,
            App.instance.router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = View.inflate(context, R.layout.fragment_user, null)

    override fun setText(text: String) {
        user_login.text = text
    }

    override fun backPressed() = presenter.backPressed()
}