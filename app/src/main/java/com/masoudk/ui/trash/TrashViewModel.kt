package com.masoudk.ui.trash

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.masoudk.domain.MessageRepository
import com.masoudk.ui.base.BaseViewModel
import com.masoudk.ui.model.Message
import com.masoudk.ui.model.mapToDomain
import com.masoudk.ui.model.mapToView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class TrashViewModel constructor(private val namesRepository: MessageRepository) : BaseViewModel() {

    val messages : Flow<PagingData<Message>> = namesRepository
        .getTrash(PagingConfig(10))
        .map { it.map { message -> message.mapToView() } }
        .cachedIn(viewModelScope)

}
