package com.example.senanas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.senanas.data.TicketDataLayerSingleton
import com.example.senanas.views.ticketRecyclerView.TicketListAdapter

class TicketsActivity : AppCompatActivity(),NavigateOnClickLListener {

    private lateinit var ticketDataLayerSingleton: TicketDataLayerSingleton
    private lateinit var ticketRecyclerView: RecyclerView

    private lateinit var homeButton: Button
    private lateinit var profileButton: Button
    private lateinit var ticketButton: Button
    private var token:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)

        initView()
        initViewModel()
        getToken()

        ticketDataLayerSingleton.getTicketListViewModel().getTicket(token!!)

        ticketDataLayerSingleton.getTicketListViewModel().tickets.observe(this, Observer { result ->
          result?.onSuccess { ticket ->
              val ticketListAdapter = TicketListAdapter(ticket!!,this)
              ticketRecyclerView.setAdapter(ticketListAdapter)
              val linearLayoutManager = LinearLayoutManager(this)
              ticketRecyclerView.layoutManager = linearLayoutManager
          }
         })

        homeButton.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }

        profileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initView(){
        homeButton = this.findViewById(R.id.homeButtonFromTicketList)
        profileButton = this.findViewById(R.id.profileButtonFromTicketList)
        ticketRecyclerView = this.findViewById(R.id.ticketrecyclerView)
    }

    private fun initViewModel(){

        ticketDataLayerSingleton = TicketDataLayerSingleton
        ticketDataLayerSingleton.createRetrofitClient()
        ticketDataLayerSingleton.createTicketService()
        ticketDataLayerSingleton.initTicketListViewModel()

    }

    private fun getToken():String {
        val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("token", "DefaultName")
        return token!!
    }

    override fun navigate(id: Int?) {
        Intent(this, DetailTicketActivity::class.java).also {
            it.putExtra("ID_TICKET",id)
            startActivity(it)
        }
    }
}