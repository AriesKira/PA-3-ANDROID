package com.example.senanas.views.ticketRecyclerView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.senanas.R
import com.example.senanas.modelDto.TicketListDto

class TicketListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private var nameTextView : TextView
    private var statusTextView : TextView

    init {
        this.nameTextView = itemView.findViewById(R.id.title_ticket_item_text_view)
        this.statusTextView = itemView.findViewById(R.id.status_ticket_item_text_view)
    }

    fun bind(ticket: TicketListDto) {
        this.nameTextView.text = ticket.title
        this.statusTextView.text = ticket.status
    }
}