package com.example.github_client.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.github_client.ApiHolder
import com.example.github_client.App
import com.example.github_client.R
import com.example.github_client.mvp.model.entity.GithubUser
import com.example.github_client.mvp.model.repo.retrofit.RetrofitGithubRepositoryRepo
import com.example.github_client.mvp.presenter.UserPresenter
import com.example.github_client.mvp.view.UserView
import com.example.github_client.ui.BackButtonListener
import com.example.github_client.ui.adapter.RepositoriesUserRVAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
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
            AndroidSchedulers.mainThread(),
            RetrofitGithubRepositoryRepo(ApiHolder().api),
            user,
            App.instance.router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_user, null)

    var adapter: RepositoriesUserRVAdapter? = null

    override fun init() {
        rv_repo.layoutManager = LinearLayoutManager(context)
        adapter = RepositoriesUserRVAdapter(presenter.repositoryListPresenter)
        rv_repo.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}
