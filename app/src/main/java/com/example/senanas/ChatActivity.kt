package com.example.senanas

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.senanas.data.ChatDataLayerSingleton
import com.example.senanas.modelDto.SendMessageDto
import com.example.senanas.views.chatRecyclerView.ChatListAdapter

class ChatActivity : AppCompatActivity() {
    private lateinit var sendButton: Button
    private lateinit var messageEditText: EditText
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatDataLayerSingleton:ChatDataLayerSingleton

    private var token:String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initView()
        initViewModel()
        getToken()


        if(this.intent.hasExtra("ID_CHAT")) {
            val chatId = intent.getIntExtra("ID_CHAT", -1)
            chatDataLayerSingleton.getChatViewModel().getChat(token!!,chatId)
            chatDataLayerSingleton.getChatViewModel().chat.observe(this, Observer { result ->
                result?.onSuccess {
                    val chatViewListAdapter = ChatListAdapter(it!!)
                    chatRecyclerView.adapter = chatViewListAdapter
                    val linearLayoutManager = LinearLayoutManager(this)
                    chatRecyclerView.layoutManager = linearLayoutManager

                    chatRecyclerView.post {
                        chatRecyclerView.adapter?.let {
                            if(it.itemCount > 0) {
                                chatRecyclerView.smoothScrollToPosition(chatViewListAdapter.itemCount - 1)
                            }
                        }

                    }

                }
            })

            chatDataLayerSingleton.getChatViewModel().addMessage.observe(this) { result ->
                if (result) {
                    chatDataLayerSingleton.getChatViewModel().getChat(token!!,chatId)
                    messageEditText.setText("")
                    chatRecyclerView.post {

                        chatRecyclerView.adapter?.let {
                            if(it.itemCount > 0) {
                                chatRecyclerView.scrollToPosition(it.itemCount -1 )
                            }

                        }
                    }
                }
            }

            sendButton.setOnClickListener {
                if(messageEditText.text.isNotEmpty()) {
                    val sendMessageDto = SendMessageDto(chatId=chatId, message = messageEditText.text.toString())
                    chatDataLayerSingleton.getChatViewModel().sendMessage(token!!,sendMessageDto)
                }
            }

        }


    }



    private fun initView(){
        sendButton = this.findViewById(R.id.sendMessageButton)
        messageEditText = this.findViewById(R.id.sendMessageEditText)
        chatRecyclerView = this.findViewById(R.id.chatBody)
    }

    private fun initViewModel(){
        chatDataLayerSingleton = ChatDataLayerSingleton
        chatDataLayerSingleton.createRetrofitClient()
        chatDataLayerSingleton.createChatService()
        chatDataLayerSingleton.initChatViewModel()

    }

    private fun getToken():String {
        val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("token", "DefaultName")
        return token!!
    }
}