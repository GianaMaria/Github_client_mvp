package com.example.github_client.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.github_client.R
import com.example.github_client.mvp.presenter.list.IRepositoryListPresenter
import com.example.github_client.mvp.view.list.RepositoryItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_repo.view.*

class RepositoriesUserRVAdapter(private val presenter: IRepositoryListPresenter) :
    RecyclerView.Adapter<RepositoriesUserRVAdapter.ViewHolder>() {

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer, RepositoryItemView {
        override fun setName(name: String?) = with(containerView) {
            tv_name.text = name
        }

        override fun setForksCount(count: Int?) = with(containerView) {
            tv_count.text = count.toString()
        }

        override var pos = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false))

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }


}