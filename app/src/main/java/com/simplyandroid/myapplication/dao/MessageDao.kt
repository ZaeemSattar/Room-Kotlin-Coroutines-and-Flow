package com.simplyandroid.myapplication.dao

import androidx.room.*
import com.simplyandroid.myapplication.models.MessagesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Query("SELECT * FROM message")
    fun getAll(): Flow<List<MessagesModel>>

    @Query("SELECT * FROM message WHERE message LIKE :message")
    fun findByMessage(message: String): MessagesModel

    @Query("SELECT * FROM message WHERE message LIKE :id")
    fun findById(id: String): Flow<MessagesModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg message: MessagesModel)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(message: MessagesModel)

    @Delete
    suspend fun delete(message: MessagesModel)

    @Update
    suspend fun updateMessage(vararg messages: MessagesModel)

}