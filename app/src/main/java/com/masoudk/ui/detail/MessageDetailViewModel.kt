package com.masoudk.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.masoudk.domain.MessageRepository
import com.masoudk.ui.base.BaseViewModel
import com.masoudk.ui.base.LiveEvent
import com.masoudk.ui.model.Message
import kotlinx.coroutines.launch

class MessageDetailViewModel(
    private val repository: MessageRepository
    ) : BaseViewModel() {

    val moveBackEvent = LiveEvent<Boolean>()

    private var _message = MutableLiveData<Message>()
    val message : LiveData<Message> = _message

    fun setUser(message: Message){
        _message.value = message

        setMessageRead(true)
    }

    private fun setMessageRead(read: Boolean){

        val messageId = message.value?.id ?: return

        ioScope.launch {
            repository.setMessageStatus(messageId, read = read)
        }
    }

    private fun moveMessageToTrash(){
        val messageId = message.value?.id ?: return

        ioScope.launch {
            repository.moveMessageToTrash(messageId)
            moveBackEvent.postValue(true)
        }
    }

    private fun restoreMessageToInbox(){
        val messageId = message.value?.id ?: return

        ioScope.launch {
            repository.restoreMessageToInbox(messageId)
            moveBackEvent.postValue(true)
        }
    }

    private fun deleteMessageCompletely(){
        val messageId = message.value?.id ?: return

        ioScope.launch {
            repository.deleteMessageCompletely(messageId)
            moveBackEvent.postValue(true)
        }
    }

    fun unreadClick(){
        setMessageRead(false)
        moveBackEvent.postValue(true)
    }

    fun restoreClick(){
        restoreMessageToInbox()
        moveBackEvent.postValue(true)
    }

    fun deleteClick(){

        val message = message.value ?: return

        if(message.isDelete){
            deleteMessageCompletely()
        }else{
            moveMessageToTrash()
        }

    }

}
