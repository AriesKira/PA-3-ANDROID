package com.example.senanas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.senanas.data.TicketDataLayerSingleton

class DetailTicketActivity : AppCompatActivity() {

    private lateinit var ticketDataLayerSingleton: TicketDataLayerSingleton

    private lateinit var homeButton: Button
    private lateinit var myTicketButton: Button
    private lateinit var profileButton: Button
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var statusTextView: TextView
    private lateinit var chatButton: Button
    private var chatId:Int? = null

    private var token:String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_ticket)

        initViewModel()
        getToken()
        initView()
        initNavbar()

        if (this.intent.hasExtra("ID_TICKET")) {
            val id = intent.getIntExtra("ID_TICKET", -1)
            ticketDataLayerSingleton.getDetailTicketListViewModel().getDetailTicket(token!!,id)

            ticketDataLayerSingleton.getDetailTicketListViewModel().detailTicket.observe(this,
                Observer { result ->
                    result.onSuccess {
                        it?.let {
                            titleTextView.text = it.title
                            descriptionTextView.text = it.description
                            statusTextView.text = "status : ${it.status}"
                            chatId = it.chatId
                        }
                    }

                    result.onFailure {
                        Toast.makeText(this, "Une erreur est survenue", Toast.LENGTH_SHORT).show()
                    }
                })

            chatButton.setOnClickListener {
                if(chatId != null) {
                    val intent = Intent(this, ChatActivity::class.java)
                    intent.putExtra("ID_CHAT",chatId)
                    startActivity(intent)
                }

            }
        }
    }


    private fun initViewModel(){

        ticketDataLayerSingleton = TicketDataLayerSingleton
        ticketDataLayerSingleton.createRetrofitClient()
        ticketDataLayerSingleton.createTicketService()
        ticketDataLayerSingleton.initDetailTicketListViewModel()

    }


    private fun initView(){
        homeButton = this.findViewById(R.id.homeButtonFromTicketDetail)
        profileButton = this.findViewById(R.id.profileButtonFromTicketDetail)
        myTicketButton = this.findViewById(R.id.ticketButtonFromTicketDetail)
        titleTextView = this.findViewById(R.id.titleDetailTicketTextView)
        descriptionTextView = this.findViewById(R.id.descriptionDetailTicketTextView)
        statusTextView = this.findViewById(R.id.statusDetailTicketTextView)
        chatButton = this.findViewById(R.id.chatDetailTicketButton)
    }


    private fun initNavbar(){
        homeButton.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }
        profileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        myTicketButton.setOnClickListener {
            val intent = Intent(this, TicketsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getToken():String {
        val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("token", "DefaultName")
        return token!!
    }
}