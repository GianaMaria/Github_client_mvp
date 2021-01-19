package com.example.github_client.mvp.model.entity

class GithubUsersRepository {

    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers() : List<GithubUser>{
        return repositories
    }
}