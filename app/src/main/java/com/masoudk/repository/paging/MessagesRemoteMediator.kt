package com.masoudk.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.masoudk.datasource.local.model.DBMessage
import com.masoudk.datasource.local.model.mapToLocal
import com.masoudk.repository.datasource.LocalDataSource
import com.masoudk.repository.datasource.RemoteDataSource
import com.masoudk.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

@OptIn(ExperimentalPagingApi::class)
class MessagesRemoteMediator(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : RemoteMediator<Int, DBMessage>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, DBMessage>
    ): MediatorResult {

        try{

            val id = when (loadType) {
                LoadType.REFRESH -> ""
                LoadType.APPEND -> {
                    state.lastItemOrNull()?.id ?: ""

                }
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            }

            val response = remoteDataSource.getMessages(id)

            return when(response){
                is ResultWrapper.Success -> {
                    withContext(Dispatchers.IO){
                        localDataSource.saveMessage(response.value)
                    }

                    MediatorResult.Success(endOfPaginationReached = response.value.isEmpty())
                }
                else -> MediatorResult.Error(Throwable("Error"))
            }

        }catch (e: Exception){
            return MediatorResult.Error(e)
        }

    }
}