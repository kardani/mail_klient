package com.masoudk.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.masoudk.ui.R

class LoaderStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        return LoaderViewHolder.getInstance(parent, retry)
    }

    /**
     * view holder class for footer loader and error state handling
     */
    class LoaderViewHolder(view: View, retry: () -> Unit) : RecyclerView.ViewHolder(view) {

        companion object {
            //get instance of the DoggoImageViewHolder
            fun getInstance(parent: ViewGroup, retry: () -> Unit): LoaderViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.layout_list_loader, parent, false)
                return LoaderViewHolder(view, retry)
            }
        }

        private val progressBar: ProgressBar = itemView.findViewById(R.id.pbLoader)
        private val errorMsg: TextView = itemView.findViewById(R.id.tvError)
        private val retry: Button = itemView.findViewById<Button>(R.id.btnRetry)
            .also { it.setOnClickListener { retry.invoke() } }

        init {
            view.findViewById<Button>(R.id.btnRetry).setOnClickListener {
                retry()
            }
        }

        fun bind(loadState: LoadState) {


            if (loadState is LoadState.Error) {
                errorMsg.text = loadState.error.localizedMessage
            }
            progressBar.isVisible = loadState is LoadState.Loading
            retry.isVisible = loadState !is LoadState.Loading
            errorMsg.isVisible = loadState !is LoadState.Loading
        }
    }
}