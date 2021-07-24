package com.masoudk.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.masoudk.ui.model.Message

class MessageDetailViewModel : ViewModel() {

    private var _user = MutableLiveData<Message>()
    val user : LiveData<Message> = _user

    fun setUser(user: Message){
        _user.value = user
    }

}
