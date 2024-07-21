package com.example.senanas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.senanas.data.TicketDataLayerSingleton
import com.example.senanas.model.CreateTicketDto

class TicketActivityActivity : AppCompatActivity() {
    private lateinit var homeButton: Button
    private lateinit var profileButton: Button
    private lateinit var ticketButton: Button
    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var sendTicketButton:Button

    private lateinit var ticketDataLayerSingleton: TicketDataLayerSingleton
    private var token:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_activity)

        initView()
        initViewModel()
        getToken()


        ticketDataLayerSingleton.getTicketViewModel().createTicketResult.observe(this) { result ->
            val data = result.getOrNull()
            data?.let { responseRegisterDto ->
                val id = responseRegisterDto.message.toInt()
                Intent(this, DetailTicketActivity::class.java).also { intent ->
                    intent.putExtra("ID_TICKET",id)
                    startActivity(intent)
                }
            }
        }


        homeButton.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }

        sendTicketButton.setOnClickListener {
            val data = CreateTicketDto(title = titleEditText.text.toString(), description = descriptionEditText.text.toString())
            ticketDataLayerSingleton.getTicketViewModel().createTicket(token!!,data)
        }


    }


    private fun initViewModel(){

        ticketDataLayerSingleton = TicketDataLayerSingleton
        ticketDataLayerSingleton.createRetrofitClient()
        ticketDataLayerSingleton.createTicketService()
        ticketDataLayerSingleton.initTicketViewModel()

    }

    private  fun initView(){
        homeButton = this.findViewById(R.id.homeButtonFromTicket)
        profileButton = this.findViewById(R.id.profileButtonFromTicket)
        ticketButton = this.findViewById(R.id.ticketButtonFromTicket)
        titleEditText = this.findViewById(R.id.createTicketTitleEditText)
        descriptionEditText = this.findViewById(R.id.createTicketDescriptionEditText)
        sendTicketButton = this.findViewById(R.id.sendTicketButton)

    }

    private fun getToken():String {
        val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("token", "DefaultName")
        return token!!
    }
}