package com.masoudk.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.masoudk.domain.MessageRepository
import com.masoudk.ui.model.Message

class MessageDetailViewModel(
    private val repository: MessageRepository
    ) : ViewModel() {

    private var _message = MutableLiveData<Message>()
    val message : LiveData<Message> = _message

    fun setUser(user: Message){
        _message.value = user
    }

    fun unreadClick(){

    }

    fun deleteClick(){

    }

}
