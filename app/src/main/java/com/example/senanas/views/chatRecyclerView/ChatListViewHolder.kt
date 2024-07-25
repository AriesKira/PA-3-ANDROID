package com.example.senanas.views.chatRecyclerView

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.senanas.Formatter
import com.example.senanas.R
import com.example.senanas.model.MessageDto
import com.example.senanas.model.TicketListDto
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class ChatListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var nameTextView : TextView
    var senderTextView: TextView
    var dateTextView: TextView

    init {
        this.nameTextView = itemView.findViewById(R.id.chat_item_message_text_view)
        this.senderTextView = itemView.findViewById(R.id.chat_item_sender_text_view)
        this.dateTextView = itemView.findViewById(R.id.chat_item_date_text_view)
    }

    fun bind(message: MessageDto) {
        this.nameTextView.text = message.message
        this.senderTextView.setText("${message.sender} :")
        this.dateTextView.text = Formatter.formatDate(message.date)

    }
}