package com.example.senanas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.senanas.data.UserDataLayerSingleton
import com.example.senanas.views.category_recycler_view.CategoryListAdapter

class TestActivity : AppCompatActivity(),NavigateOnClickLListener {
    private lateinit var userDataLayer: UserDataLayerSingleton
    private lateinit var recyclerViewCategories: RecyclerView
    private lateinit var profileButton: Button
    private lateinit var ticketButton: Button

    private var token:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        getToken()

        userDataLayer = UserDataLayerSingleton
        userDataLayer.createRetrofitClient()
        userDataLayer.createTodoService()
        userDataLayer.initHomeViewModel()
        userDataLayer.getHomeViewModel().getCategories(token!!)
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

    private fun getToken():String {
        val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("token", "DefaultName")
        return token!!
    }

    override fun navigate(id:Int?) {
        Intent(this, TicketActivityActivity::class.java).also {
            startActivity(it)
        }
    }
}


interface NavigateOnClickLListener {
    fun navigate(id:Int?)
}