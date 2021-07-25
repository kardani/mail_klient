package com.masoudk.ui.inbox

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.masoudk.datasource.local.model.DBMessage
import com.masoudk.domain.MessageRepository
import com.masoudk.ui.model.Message
import com.masoudk.ui.base.BaseViewModel
import com.masoudk.ui.model.mapToView
import com.masoudk.utils.ResultWrapper
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class InboxViewModel constructor(private val namesRepository: MessageRepository) : BaseViewModel() {


//    private var _users = MutableLiveData<List<Message>>()
//    var messages : LiveData<List<Message>?>? = null

    val jdjsh : Flow<PagingData<DBMessage>> = namesRepository.getInbox(PagingConfig(20)).cachedIn(viewModelScope)

//    init {
//
//        _users.value = arrayListOf()
//
//        ioScope.launch {
//
//            messages = namesRepository.getMessagesLive().map { it.mapToView() }
//
//            val response = namesRepository.getMessages(1)
//            viewModelScope.launch {
//
////                when(response){
////                    is ResultWrapper.Success -> {_users.postValue(response.value.mapToView())}
////                    is ResultWrapper.GenericError -> {}
////                    is ResultWrapper.NetworkError -> {}
////                }
//
//            }
//        }
//
//    }


}
