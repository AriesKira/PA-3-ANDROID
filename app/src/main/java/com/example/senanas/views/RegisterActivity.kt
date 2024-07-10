package com.example.senanas.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myfirstapp.data.UserDataLayerSingleton
import com.example.senanas.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var userDataLayer: UserDataLayerSingleton
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userDataLayer = UserDataLayerSingleton
        userDataLayer.createRetrofitClient()
        userDataLayer.createTodoService()
        userDataLayer.initViewModel()

        setContentView(R.layout.activity_register)
        registerButton = findViewById(R.id.registerButton)
        registerButton.setOnClickListener {
            userDataLayer.getRegisterViewModel().test()
        }




    }
}