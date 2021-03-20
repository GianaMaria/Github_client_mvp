package com.example.github_client.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}