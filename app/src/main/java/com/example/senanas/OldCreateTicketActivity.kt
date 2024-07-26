package com.example.senanas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.senanas.modelDto.CreateTicketDto

class OldCreateTicketActivity : AppCompatActivity() {
    private lateinit var homeButton: Button
    private lateinit var profileButton: Button
    private lateinit var ticketButton: Button
    private lateinit var createTicketButton: Button
    private lateinit var titleTicketEditText: EditText
    private lateinit var descriptionTicketEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ticket)
        homeButton = this.findViewById(R.id.homeButtonFromTicket)
        profileButton = this.findViewById(R.id.profileButtonFromTicket)
        ticketButton = this.findViewById(R.id.ticketButtonFromTicket)
        createTicketButton = this.findViewById(R.id.sendTicketButton)
        titleTicketEditText = this.findViewById(R.id.createTicketTitleEditText)
        descriptionTicketEditText = this.findViewById(R.id.createTicketDescriptionEditText)




        homeButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        /*
        createTicketButton.setOnClickListener {
            val createTicketDto = CreateTicketDto(title = titleTicketEditText.text.toString(), description = descriptionTicketEditText.text.toString())

        }*/
    }
}