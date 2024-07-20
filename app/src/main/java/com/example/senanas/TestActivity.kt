package com.example.senanas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.senanas.data.UserDataLayerSingleton
import com.example.senanas.views.category_recycler_view.CategoryListAdapter

class TestActivity : AppCompatActivity(),TodoOnClickLListener {
    private lateinit var userDataLayer: UserDataLayerSingleton
    private lateinit var recyclerViewCategories: RecyclerView
    private lateinit var profileButton: Button
    private lateinit var ticketButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        userDataLayer = UserDataLayerSingleton
        userDataLayer.createRetrofitClient()
        userDataLayer.createTodoService()
        userDataLayer.initHomeViewModel()
        userDataLayer.getHomeViewModel().getCategories("BEARER eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsImVtYWlsIjoidXNlckBnbWFpbC5jb20iLCJyb2xlIjoiVVNFUiIsImlhdCI6MTcyMTE0MjEyOH0.WNA7sGEjlF-f0uTZa1PUIrBLZPrjEdDLJ61UXAeCDyU")
        recyclerViewCategories = findViewById(R.id.recyclerViewCategories2)
        profileButton = this.findViewById(R.id.profileButton)
        ticketButton = this.findViewById(R.id.ticketButton)

        profileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        ticketButton.setOnClickListener {
            val intent = Intent(this, TicketsActivity::class.java)
            startActivity(intent)
        }

        userDataLayer.getHomeViewModel().categories.observe(this, Observer { result ->

            result.onSuccess { categories ->
                categories?.let {
                    val categoriesAdapter = CategoryListAdapter(it,this)
                    recyclerViewCategories.setAdapter(categoriesAdapter)
                    val gridLayoutManager = GridLayoutManager(this, 2)
                    recyclerViewCategories.layoutManager = gridLayoutManager
                }
            }
        })


    }

    override fun displayTodoDetail() {
        Intent(this, TicketActivityActivity::class.java).also {
            startActivity(it)
        }
    }
}

interface TodoOnClickLListener {
    fun displayTodoDetail()
}