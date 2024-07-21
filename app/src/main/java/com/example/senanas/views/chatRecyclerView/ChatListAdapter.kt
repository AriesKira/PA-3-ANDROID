package com.example.senanas.views.chatRecyclerView

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.senanas.R
import com.example.senanas.model.MessageDto

class ChatListAdapter(
    var chatList: List<MessageDto>
):RecyclerView.Adapter<ChatListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        val chatView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat,parent,false)
        return ChatListViewHolder(chatView)
    }

    override fun getItemCount(): Int {
        return this.chatList.size
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        val currentMessage = this.chatList[position]
        holder.bind(currentMessage)

        when (currentMessage.isFromSupport) {
            true -> {
                holder.itemView.setBackgroundColor(holder.itemView.context.getColor(R.color.lightGreen))
                holder.dateTextView.setTextColor(holder.itemView.context.getColor(R.color.white))
                holder.nameTextView.setTextColor(holder.itemView.context.getColor(R.color.white))
                holder.senderTextView.setTextColor(holder.itemView.context.getColor(R.color.white))

            }
            else -> {
                holder.itemView.setBackgroundColor(holder.itemView.context.getColor(R.color.lightGray))
            }
        }
    }

}