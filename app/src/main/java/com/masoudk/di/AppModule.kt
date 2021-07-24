package com.masoudk.di

import com.masoudk.datasource.local.AppDatabase
import com.masoudk.datasource.local.LocalDataSourceImpl
import com.masoudk.datasource.network.RemoteDataSourceImpl
import com.masoudk.datasource.network.EmailEndpoint
import com.masoudk.domain.MessageRepository
import com.masoudk.repository.MessageRepositoryImpl
import com.masoudk.repository.datasource.LocalDataSource
import com.masoudk.repository.datasource.RemoteDataSource
import com.masoudk.ui.inbox.InboxViewModel
import com.masoudk.ui.detail.MessageDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {provideRemoteDataSource(get())}
    single {provideLocalDataSource(get())}
    single {provideUsersRepository(get(), get())}
    viewModel { InboxViewModel(get()) }
    viewModel { MessageDetailViewModel() }

}

private fun provideUsersRepository(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource) : MessageRepository{
    return MessageRepositoryImpl(remoteDataSource, localDataSource)
}

private fun provideRemoteDataSource(emailEndpoint: EmailEndpoint) : RemoteDataSource{
    return RemoteDataSourceImpl(emailEndpoint)
}

private fun provideLocalDataSource(appDatabase: AppDatabase) : LocalDataSource{
    return LocalDataSourceImpl(appDatabase.messagesDao())
}