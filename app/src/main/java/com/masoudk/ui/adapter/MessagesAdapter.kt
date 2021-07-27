package com.masoudk.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.masoudk.datasource.local.model.DBMessage
import com.masoudk.ui.databinding.LayoutMessageItemBinding
import com.masoudk.ui.model.Message

class MessagesAdapter constructor(private val clickListener: ClickListener?) : PagingDataAdapter<Message, MessagesAdapter.ViewHolder>(
    Message.DiffCallBack
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position) ?: return

        holder.bind(item)

        holder.itemView.setOnClickListener {
            clickListener?.click(item)
        }

    }

    class ViewHolder(private val binding: LayoutMessageItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(model: Message){
            binding.message = model
            binding.executePendingBindings()
        }

        companion object{

            fun from(parent: ViewGroup) : ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = LayoutMessageItemBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }

        }

    }

    companion object DiffCallBack : DiffUtil.ItemCallback<DBMessage>(){
        override fun areItemsTheSame(oldItem: DBMessage, newItem: DBMessage): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DBMessage, newItem: DBMessage): Boolean {
            return oldItem == newItem
        }

    }

    interface ClickListener{
        fun click(item: Message)
    }
}