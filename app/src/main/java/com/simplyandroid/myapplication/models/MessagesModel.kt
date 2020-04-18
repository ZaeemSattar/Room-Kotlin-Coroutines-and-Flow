package com.simplyandroid.myapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message")
data class MessagesModel(
    @PrimaryKey(autoGenerate = true) var id: Int, var message: String,
    var timeStamp: String
)