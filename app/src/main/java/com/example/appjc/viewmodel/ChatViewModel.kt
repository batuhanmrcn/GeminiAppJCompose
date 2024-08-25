package com.example.appjc.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appjc.constants.Constants
import com.example.appjc.model.MessageModel
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    // Observable State List
    val messageList: SnapshotStateList<MessageModel> = mutableStateListOf()

    val generativeModel : GenerativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = Constants.apiKey
    )

    fun sendMessage(question: String) {
        viewModelScope.launch {
            val chat = generativeModel.startChat()
            messageList.add(
                MessageModel(question, "user")
            )

            val response = chat.sendMessage(question)
            messageList.add(
                MessageModel(response.text.toString(), "model")
            )
            Log.i("Response from", response.text.toString())
        }
    }
}
    /*
    val messageList by lazy {
        mutableListOf<MessageModel>()
    }

    val generativeModel : GenerativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash", //if it's doesn't work change modelName !!
        apiKey = Constants.apiKey
    )

    fun sendMessage(question : String){
        viewModelScope.launch {
            val chat = generativeModel.startChat()
            messageList.add(
                MessageModel(question,"user")
            )

            val response = chat.sendMessage(question)
            messageList.add(
                MessageModel(response.text.toString(),"model")
            )
            Log.i("Response from" , response.text.toString())
        }
    }
}

     */