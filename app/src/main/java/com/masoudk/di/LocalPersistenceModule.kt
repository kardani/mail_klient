package com.masoudk.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.masoudk.datasource.local.AppDatabase
import org.koin.dsl.module

val localPersistenceModule = module{
    single { provideRoomDataBase(get()) }
}

private fun provideRoomDataBase(context: Context) : AppDatabase {
    return Room
        .databaseBuilder(context, AppDatabase::class.java, "mail_klient_database")
        .fallbackToDestructiveMigration()
        .build()
}

//private fun provideUserLocalDataSource(preferences: SharedPreferences, editor: SharedPreferences.Editor) : UserLocalDataSource {
//    return UserLocalDataSourceImpl(preferences, editor)
//}