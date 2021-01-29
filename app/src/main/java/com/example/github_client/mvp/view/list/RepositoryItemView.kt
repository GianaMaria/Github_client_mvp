package com.example.github_client.mvp.view.list

interface RepositoryItemView : IItemView {
    fun setName(name: String?)
    fun setForksCount(count: Int?)
}