package com.example.senanas.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.senanas.HomeActivity
import com.example.senanas.ProfileActivity
import com.example.senanas.R
import com.example.senanas.TestActivity
import com.example.senanas.Validator
import com.example.senanas.data.UserDataLayerSingleton
import com.example.senanas.model.LoginDto
import com.example.senanas.model.RegisterDto

class MainActivity : AppCompatActivity() {
    private lateinit var registerButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var userDataLayer: UserDataLayerSingleton
    private lateinit var emailErrorTextView : TextView
    private lateinit var passwordErrorTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userDataLayer = UserDataLayerSingleton
        userDataLayer.createRetrofitClient()
        userDataLayer.createTodoService()
        userDataLayer.initLoginViewModel()


        setContentView(R.layout.activity_main)

        userDataLayer.getLoginViewModelViewModel().loginResult.observe(this, Observer { result ->
            result.fold(
                onSuccess = { loginResponse ->
                    val token = loginResponse?.token
                    val user = loginResponse?.user
                    println(loginResponse)
                    val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("token", "BEARER ${loginResponse!!.token}")
                    editor.apply()
                    val intent = Intent(this, TestActivity::class.java)
                    startActivity(intent)
                },
                onFailure = { throwable ->
                    println("Login failed: ${throwable.message}")
                    Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()

                }
            )
        })

        registerButton = findViewById(R.id.registerButton)
        loginButton = findViewById(R.id.loginButton)
        emailEditText = findViewById(R.id.email_edit_text_login_view)
        passwordEditText = findViewById(R.id.password_edit_text_login_view)

        passwordErrorTextView = findViewById(R.id.password_error_login_text_view)
        emailErrorTextView = findViewById(R.id.email_error_login_text_view)

        registerButton.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }

        loginButton.setOnClickListener{
            clearError()
            val isValid = checkForm()

            if(isValid == 0) {
                val loginDto = fromFormToLoginDto()
                userDataLayer.getLoginViewModelViewModel().login(loginDto)
            }


        }

    }



    private fun fromFormToLoginDto() : LoginDto {
        val email = this.emailEditText.text.toString()
        val password = this.passwordEditText.text.toString()

        return LoginDto(
            email,
            password,
        )
    }

    private fun checkForm() : Int {
        var errors = 0
        if(!Validator.isValidEmail(this.emailEditText.text.toString()) || this.emailEditText.text.toString().isEmpty()) {
            this.emailErrorTextView.visibility = View.VISIBLE
            errors += 1
        }

        if(this.passwordEditText.text.toString().isEmpty()) {
            this.passwordErrorTextView.visibility = View.VISIBLE
            errors += 1
        }

        return errors
    }

    private fun clearError() {
        this.passwordErrorTextView.visibility = View.INVISIBLE
        this.emailErrorTextView.visibility = View.INVISIBLE
    }
}