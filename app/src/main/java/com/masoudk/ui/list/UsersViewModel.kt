package com.masoudk.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.masoudk.domain.MessageRepository
import com.masoudk.ui.model.Message
import com.masoudk.ui.base.BaseViewModel
import com.masoudk.ui.model.mapToView
import com.masoudk.utils.ResultWrapper
import kotlinx.coroutines.launch

class UsersViewModel constructor(private val namesRepository: MessageRepository) : BaseViewModel() {


    private var _users = MutableLiveData<List<Message>>()
    val users : LiveData<List<Message>> = _users

    init {

        _users.value = arrayListOf()

        ioScope.launch {
            val response = namesRepository.getMessages(1)
            viewModelScope.launch {

                when(response){
                    is ResultWrapper.Success -> {_users.postValue(response.value.mapToView())}
                    is ResultWrapper.GenericError -> {}
                    is ResultWrapper.NetworkError -> {}
                }

            }
        }

    }


}
