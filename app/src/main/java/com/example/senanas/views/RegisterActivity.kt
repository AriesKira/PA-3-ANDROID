package com.example.senanas.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.senanas.data.UserDataLayerSingleton
import com.example.senanas.R
import com.example.senanas.Validator
import com.example.senanas.model.RegisterDto

class RegisterActivity : AppCompatActivity(),RedirectToLogin {

    private lateinit var userDataLayer: UserDataLayerSingleton
    private lateinit var registerButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var firstnameEditText: EditText
    private lateinit var lastnameEditText: EditText
    private lateinit var emailErrorTextView: TextView
    private lateinit var passwordErrorTextView: TextView
    private lateinit var firstnameErrorTextView: TextView
    private lateinit var lastnameErrorTextView: TextView
    private val validator = Validator;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userDataLayer = UserDataLayerSingleton
        userDataLayer.createRetrofitClient()
        userDataLayer.createTodoService()
        userDataLayer.initViewModel()

        setContentView(R.layout.activity_register)

        emailErrorTextView = findViewById(R.id.email_error_text_view)
        passwordErrorTextView = findViewById(R.id.password_error_text_view)
        firstnameErrorTextView = findViewById(R.id.firstname_error_text_view)
        lastnameErrorTextView = findViewById(R.id.lastname_error_text_view)

        emailEditText = findViewById(R.id.email_edit_text_register_view)
        passwordEditText = findViewById(R.id.password_edit_text_register_view)
        lastnameEditText = findViewById(R.id.lastname_edit_text_register_view)
        firstnameEditText = findViewById(R.id.firstname_edit_text_register_view)

        registerButton = findViewById(R.id.registerButton)
        registerButton.setOnClickListener {
            clearError()
            val isValid = checkForm()

            if(isValid == 0) {
                val registerDto = fromFormToRegisterDto()
                userDataLayer.getRegisterViewModel().registerResult.observe(this, Observer {
                    it.onSuccess {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    it.onFailure { exception ->
                        Toast.makeText(this, "Inscription échouée : ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
                })
                userDataLayer.getRegisterViewModel().register(registerDto)


            }
        }


    }


    private fun fromFormToRegisterDto() : RegisterDto {
        val firstname = this.firstnameEditText.text.toString()
        val lastname = this.lastnameEditText.text.toString()
        val email = this.emailEditText.text.toString()
        val password = this.passwordEditText.text.toString()

        return RegisterDto(
            email,
            password,
            firstname,
            lastname
        )
    }


    private fun checkForm() : Int {
        var errors = 0
        if(!validator.isValidEmail(this.emailEditText.text.toString()) || this.emailEditText.text.toString().isEmpty()) {
            this.emailErrorTextView.visibility = View.VISIBLE
            errors += 1
        }

        if(this.passwordEditText.text.toString().isEmpty()) {
            this.passwordErrorTextView.visibility = View.VISIBLE
            errors += 1
        }

        if(this.firstnameEditText.text.toString().isEmpty()) {
            this.firstnameErrorTextView.visibility = View.VISIBLE
            errors += 1
        }

        if(this.lastnameEditText.text.toString().isEmpty()) {
            this.lastnameErrorTextView.visibility = View.VISIBLE
            errors += 1
        }
        return errors
    }

    private fun clearError() {
        this.lastnameErrorTextView.visibility = View.INVISIBLE
        this.firstnameErrorTextView.visibility = View.INVISIBLE
        this.passwordErrorTextView.visibility = View.INVISIBLE
        this.emailErrorTextView.visibility = View.INVISIBLE
    }

    override fun redirectToLogin() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
        }
    }
}

interface RedirectToLogin {
    fun redirectToLogin()
}