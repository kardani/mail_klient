package com.masoudk.datasource.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.masoudk.datasource.local.model.DBMessage

@Dao
interface MessagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(message: DBMessage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(messages: List<DBMessage>) : LongArray

    @Update
    fun update(message: DBMessage)

    @Delete
    fun delete(message: DBMessage)

    @Query("DELETE FROM message")
    fun deleteAll()

    @Query("SELECT * FROM message WHERE isDelete <> 1 ORDER BY date DESC")
    fun getAll(): LiveData<List<DBMessage>>

    @Query("SELECT * FROM message WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): DBMessage?

    @Query("SELECT * FROM message WHERE id = :id LIMIT 1")
    fun getByIdLive(id: String): LiveData<DBMessage>


    @Query("SELECT * FROM message WHERE isDelete <> 1 ORDER BY date DESC")
    fun getInbox(): PagingSource<Int, DBMessage>

    @Query("SELECT * FROM message WHERE isDelete = 1 ORDER BY date DESC")
    fun getTrash(): PagingSource<Int, DBMessage>
}