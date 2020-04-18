package com.simplyandroid.myapplication.ui.main

import android.content.Context
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.*
import com.simplyandroid.myapplication.dao.MessageDao
import com.simplyandroid.myapplication.database.AppDatabase
import com.simplyandroid.myapplication.models.MessagesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel() : ViewModel() {


    private final val TAG = "room_app"


    private var _messages = MutableLiveData<List<MessagesModel>>()


    public var messages: LiveData<List<MessagesModel>> = _messages


    public fun saveLargeDataSet(dao: MessageDao) {
        repeat(3000)
        {
            viewModelScope.launch(Dispatchers.IO) {

                dao.add(
                    MessagesModel(
                        it,
                        "fake message #$it",
                        System.currentTimeMillis().toString()
                    )
                )

            }

        }
    }

    public fun getAllMessages(dao: MessageDao) {

        viewModelScope.launch {

            withContext(Dispatchers.IO)
            {


                dao.getAll().collect {
                    Log.d(
                        "$TAG getAllMessages",
                        "total items =" + it.size + " operation on  = " + Thread.currentThread().name
                    )
                    withContext(Dispatchers.Main)
                    {
                        Log.d(TAG, "setting value on = " + Thread.currentThread().name)

                        _messages.value = it

                    }
                }
            }
        }


    }

}
