package com.masoudk.di

import com.masoudk.datasource.network.RemoteDataSourceImpl
import com.masoudk.datasource.network.UsersEntpoint
import com.masoudk.domain.MessageRepository
import com.masoudk.repository.MessageRepositoryImpl
import com.masoudk.repository.datasource.RemoteDataSource
import com.masoudk.ui.list.UsersViewModel
import com.masoudk.ui.detail.UserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {provideUsersDataSource(get())}
    single {provideUsersRepository(get())}
    viewModel { UsersViewModel(get()) }
    viewModel { UserViewModel() }

}

private fun provideUsersRepository(remoteDataSource: RemoteDataSource) : MessageRepository{
    return MessageRepositoryImpl(remoteDataSource)
}

private fun provideUsersDataSource(usersEndpoint: UsersEntpoint) : RemoteDataSource{
    return RemoteDataSourceImpl(usersEndpoint)
}