package com.masoudk.ui.inbox

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.masoudk.datasource.local.model.DBMessage
import com.masoudk.domain.MessageRepository
import com.masoudk.ui.base.BaseViewModel
import com.masoudk.ui.model.Message
import com.masoudk.ui.model.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class InboxViewModel constructor(private val namesRepository: MessageRepository) : BaseViewModel() {

    val messages : Flow<PagingData<DBMessage>> = namesRepository.getInbox(PagingConfig(10)).cachedIn(viewModelScope)

    fun simulateReceiveNewMessage(){
        ioScope.launch {

            namesRepository.simulateReceiveNewMessage(Message.dummy().mapToDomain())
            
        }
    }

}
