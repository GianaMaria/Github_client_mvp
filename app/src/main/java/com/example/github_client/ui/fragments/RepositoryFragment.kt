package com.example.github_client.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.github_client.App
import com.example.github_client.R
import com.example.github_client.mvp.model.entity.GithubRepository
import com.example.github_client.mvp.presenter.RepositoryPresenter
import com.example.github_client.mvp.view.RepositoryView
import com.example.github_client.ui.BackButtonListener
import kotlinx.android.synthetic.main.fragment_repository.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryFragment(private val userRepository: GithubRepository) : MvpAppCompatFragment(),
    RepositoryView, BackButtonListener {

    companion object {
        fun newInstance(userRepository: GithubRepository) = RepositoryFragment(userRepository)
    }

    private val presenter by moxyPresenter {
        RepositoryPresenter(userRepository, App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_repository, container, false)

    override fun init() {
        forks_count.text = presenter.getForksCount().toString()
    }

    override fun backPressed() = presenter.backPressed()


}