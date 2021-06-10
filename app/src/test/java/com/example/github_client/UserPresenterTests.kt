package com.example.github_client

import com.example.github_client.mvp.model.entity.GithubUser
import com.example.github_client.mvp.model.repo.IGithubUserRepositories
import com.example.github_client.mvp.presenter.UserPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router


class UserPresenterTests {

    private lateinit var presenter: UserPresenter

    private var schedulerProvider = TestSchedulerProvider()

    @Mock
    private lateinit var userRepositories: IGithubUserRepositories

    @Mock
    private lateinit var user: GithubUser

    @Mock
    private lateinit var router: Router

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = UserPresenter(schedulerProvider.ui(), userRepositories, user, router)
    }

    @Test
    fun loadData_Test() {
        presenter.loadData()

        verify(, times(1).loadData(presenter))
    }
}