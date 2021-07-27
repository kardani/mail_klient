package com.masoudk.ui.inbox

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.masoudk.datasource.local.model.DBMessage
import com.masoudk.domain.MessageRepository
import com.masoudk.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class InboxViewModel constructor(namesRepository: MessageRepository) : BaseViewModel() {

    val messages : Flow<PagingData<DBMessage>> = namesRepository.getInbox(PagingConfig(10)).cachedIn(viewModelScope)

}
