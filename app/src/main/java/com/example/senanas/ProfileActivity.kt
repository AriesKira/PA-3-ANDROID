package com.example.senanas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.senanas.data.UserDataLayerSingleton
import com.example.senanas.model.UpdateUserDto

class ProfileActivity : AppCompatActivity() {

    private lateinit var userDataLayer: UserDataLayerSingleton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val emailEditText: EditText = this.findViewById(R.id.email_edit_text_profile_view)
        val firstnameEditText: EditText = this.findViewById(R.id.firstname_edit_text_profile_view)
        val lastnameEditText: EditText = this.findViewById(R.id.lastname_edit_text_profile_view)
        val saveButton: Button = this.findViewById(R.id.save_button_profile_view)
        val homeButton:Button = this.findViewById(R.id.homeButtonFromProfile)

        userDataLayer = UserDataLayerSingleton
        userDataLayer.createRetrofitClient()
        userDataLayer.createTodoService()
        userDataLayer.initProfileViewModel()
        userDataLayer.getProfileViewModel().getUserInfo("BEARER eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsImVtYWlsIjoidXNlckBnbWFpbC5jb20iLCJyb2xlIjoiVVNFUiIsImlhdCI6MTcyMTE0MjEyOH0.WNA7sGEjlF-f0uTZa1PUIrBLZPrjEdDLJ61UXAeCDyU")
        userDataLayer.getProfileViewModel().userData.observe(this) { result ->
            result.fold(
                onSuccess = {
                    emailEditText.setText(it?.email)
                    firstnameEditText.setText(it?.firstname)
                    lastnameEditText.setText(it?.lastname)
                },
                onFailure = { throwable ->
                    println("Login failed: ${throwable.message}")
                }
            )
        }

        saveButton.setOnClickListener {
        val updateUserDto = UpdateUserDto(firstname = firstnameEditText.text.toString(), lastname = lastnameEditText.text.toString(), email = emailEditText.text.toString())
        userDataLayer.getProfileViewModel().updateUserInfo("BEARER eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsImVtYWlsIjoidXNlckBnbWFpbC5jb20iLCJyb2xlIjoiVVNFUiIsImlhdCI6MTcyMTE0MjEyOH0.WNA7sGEjlF-f0uTZa1PUIrBLZPrjEdDLJ61UXAeCDyU",updateUserDto)
        }

        homeButton.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }
    }
}