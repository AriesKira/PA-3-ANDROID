package com.example.senanas.views.ticketRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.senanas.NavigateOnClickLListener
import com.example.senanas.R
import com.example.senanas.model.TicketListDto

class TicketListAdapter(
    var ticketList: List<TicketListDto>,
    private val navigateClickHandler: NavigateOnClickLListener
):RecyclerView.Adapter<TicketListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketListViewHolder {
        val categoryView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ticket, parent, false)
        return TicketListViewHolder(categoryView)
    }

    override fun getItemCount(): Int {
        return this.ticketList.size
    }

    override fun onBindViewHolder(holder: TicketListViewHolder, position: Int) {
        val currentTicket = this.ticketList[position] // Get the data at the right position
        holder.bind(currentTicket)
        holder.itemView.setOnClickListener {
            navigateClickHandler.navigate(id = currentTicket.id)
        }

    }
}