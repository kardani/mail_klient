package com.masoudk.ui.inbox

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.masoudk.domain.MessageRepository
import com.masoudk.ui.model.Message
import com.masoudk.ui.base.BaseViewModel
import com.masoudk.ui.model.mapToView
import com.masoudk.utils.ResultWrapper
import kotlinx.coroutines.launch

class InboxViewModel constructor(private val namesRepository: MessageRepository) : BaseViewModel() {


    private var _users = MutableLiveData<List<Message>>()
    var messages : LiveData<List<Message>?>? = null

    init {

        _users.value = arrayListOf()

        ioScope.launch {

            messages = namesRepository.getMessagesLive().map { it.mapToView() }

            val response = namesRepository.getMessages(1)
            viewModelScope.launch {

//                when(response){
//                    is ResultWrapper.Success -> {_users.postValue(response.value.mapToView())}
//                    is ResultWrapper.GenericError -> {}
//                    is ResultWrapper.NetworkError -> {}
//                }

            }
        }

    }


}
