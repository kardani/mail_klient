package com.masoudk.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.masoudk.domain.MessageRepository
import com.masoudk.repository.model.Message
import com.masoudk.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class UsersViewModel constructor(private val namesRepository: MessageRepository) : BaseViewModel() {


    private var _users = MutableLiveData<ArrayList<Message>>()
    val users : LiveData<ArrayList<Message>> = _users

    init {

        _users.value = arrayListOf()

        ioScope.launch {
            val response = namesRepository.getNames()
            viewModelScope.launch {
                if(response.data.isNotEmpty()){

                    val newList = arrayListOf<Message>()
                    newList.addAll(response.data)
                    _users.postValue(newList)

//                    _users.value?.let {
//                        it.addAll(response.data)
//                    }
                }
            }
        }

    }


}
