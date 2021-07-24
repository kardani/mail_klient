package com.masoudk.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.masoudk.datasource.local.model.DBMessage

@Dao
interface MessagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: DBMessage) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(messages: List<DBMessage>) : Long

    @Update
    suspend fun update(message: DBMessage)

    @Delete
    suspend fun delete(message: DBMessage)

    @Query("DELETE FROM message")
    fun deleteAll()

    @Query("SELECT * FROM message WHERE isDelete <> 1 ORDER BY date DESC")
    suspend fun getAll(): LiveData<List<DBMessage>>

    @Query("SELECT * FROM message WHERE isDelete = 1 ORDER BY date DESC")
    suspend fun getTrash(): LiveData<List<DBMessage>>

    @Query("SELECT * FROM message WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): DBMessage?

    @Query("SELECT * FROM message WHERE id = :id LIMIT 1")
    suspend fun getByIdLive(id: String): LiveData<DBMessage>?
}