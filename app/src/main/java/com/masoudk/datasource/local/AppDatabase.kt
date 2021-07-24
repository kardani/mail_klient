package com.masoudk.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.masoudk.datasource.local.model.DBMessage

@Database(entities = [DBMessage::class], version = 1, exportSchema = false)
//@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messagesDao() : MessagesDao
}