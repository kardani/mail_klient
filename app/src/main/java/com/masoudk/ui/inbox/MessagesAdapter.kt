package com.masoudk.ui.inbox

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.masoudk.ui.databinding.LayoutUserItemBinding
import com.masoudk.ui.model.Message

class MessagesAdapter constructor(private val clickListener: UsersClickListener?) : ListAdapter<Message, MessagesAdapter.ViewHolder>(
    Message.DiffCallBack
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)

        holder.itemView.setOnClickListener {
            clickListener?.let {
                it.click(item)
            }
        }

    }

    class ViewHolder(private val binding: LayoutUserItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(model: Message){
            binding.user = model
            binding.executePendingBindings()
        }

        companion object{

            fun from(parent: ViewGroup) : ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = LayoutUserItemBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }

        }

    }

}

interface UsersClickListener{
    fun click(user: Message)
}

@BindingAdapter("bindUsers")
fun bindUsersAdapter(recyclerView: RecyclerView, items: List<Message>?){

    if(recyclerView.adapter == null){
        return
    }

    items?.let {
        (recyclerView.adapter as MessagesAdapter).submitList(it)
    }

}