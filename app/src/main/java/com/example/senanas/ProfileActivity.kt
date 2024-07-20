package com.example.senanas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.senanas.data.UserDataLayerSingleton
import com.example.senanas.model.UpdateUserDto

class ProfileActivity : AppCompatActivity() {

    private lateinit var userDataLayer: UserDataLayerSingleton
    private var token:String? = null


    private lateinit var emailEditText: EditText
    private lateinit var firstnameEditText: EditText
    private lateinit var lastnameEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var zipCodeEditText: EditText
    private lateinit var cityEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var errorEmailTextView: TextView
    private lateinit var errorFirstnameTextView: TextView
    private lateinit var errorLastnameTextView: TextView
    private lateinit var errorAddressTextView: TextView
    private lateinit var errorZipCodeTextView: TextView
    private lateinit var errorCityTextView: TextView
    //private lateinit var errorPasswordTextView: TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        emailEditText = this.findViewById(R.id.email_edit_text_profile_view)
        firstnameEditText = this.findViewById(R.id.firstname_edit_text_profile_view)
        lastnameEditText = this.findViewById(R.id.lastname_edit_text_profile_view)
        addressEditText = this.findViewById(R.id.address_edit_text_profile_view)
        zipCodeEditText =  this.findViewById(R.id.zipcode_edit_text_profile_view)
        cityEditText = this.findViewById(R.id.city_edit_text_profile_view)
        //passwordEditText = this.findViewById(R.id.password_edit_text_profile_view)


        val saveButton: Button = this.findViewById(R.id.save_button_profile_view)
        val homeButton:Button = this.findViewById(R.id.homeButtonFromProfile)


         errorEmailTextView = this.findViewById(R.id.email_text_view_profile_view)
         errorFirstnameTextView = this.findViewById(R.id.firstname_text_view_profile_view)
         errorLastnameTextView = this.findViewById(R.id.lastname_text_view_profile_view)
         errorAddressTextView = this.findViewById(R.id.address_text_view_profile_view)
         errorZipCodeTextView = this.findViewById(R.id.zipcode_text_view_profile_view)
         errorCityTextView  = this.findViewById(R.id.city_text_view_profile_view)
         //errorPasswordTextView  = this.findViewById(R.id.password_text_view_profile_view)

        userDataLayer = UserDataLayerSingleton
        userDataLayer.createRetrofitClient()
        userDataLayer.createTodoService()
        userDataLayer.initProfileViewModel()
        val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("token", "DefaultName")
        println(token)
        userDataLayer.getProfileViewModel().getUserInfo(token!!)
        userDataLayer.getProfileViewModel().userData.observe(this) { result ->
            result.fold(
                onSuccess = {
                    emailEditText.setText(it?.email)
                    firstnameEditText.setText(it?.firstname)
                    lastnameEditText.setText(it?.lastname)
                    zipCodeEditText.setText(it?.zipcode.toString())
                    cityEditText.setText(it?.city)
                    addressEditText.setText(it?.address)

                },
                onFailure = { throwable ->
                    println("Login failed: ${throwable.message}")
                }
            )
        }

        saveButton.setOnClickListener {
            clearError()
            val isValid = checkForm()
            if(isValid == 0) {
                val updateUserDto = UpdateUserDto(
                    firstname = firstnameEditText.text.toString(),
                    lastname = lastnameEditText.text.toString(),
                    email = emailEditText.text.toString(),
                    zipcode = zipCodeEditText.text.toString(),
                    address = addressEditText.text.toString(),
                    city = cityEditText.text.toString()
                    )
                userDataLayer.getProfileViewModel().updateUserInfo(token!!,updateUserDto)
            }

        }

        homeButton.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkForm() : Int {

        var errors = 0
        if(!isValidEmail(this.emailEditText.text.toString()) || this.emailEditText.text.toString().isEmpty()) {
            this.errorEmailTextView.visibility = View.VISIBLE
            errors += 1
        }

        //if(this.passwordEditText.text.toString().isEmpty()) {
        //    this.errorPasswordTextView.visibility = View.VISIBLE
        //    errors += 1
        //}

        if(this.firstnameEditText.text.toString().isEmpty()) {
            this.errorFirstnameTextView.visibility = View.VISIBLE
            errors += 1
        }

        if(this.lastnameEditText.text.toString().isEmpty()) {
            this.errorLastnameTextView.visibility = View.VISIBLE
            errors += 1
        }

        if(this.addressEditText.text.toString().isEmpty()) {
            this.errorAddressTextView.visibility = View.VISIBLE
            errors += 1
        }
        if(this.cityEditText.text.toString().isEmpty()) {
            this.errorCityTextView.visibility = View.VISIBLE
            errors += 1
        }
        if(!isFrenchPostalCode(this.zipCodeEditText.text.toString()) || this.zipCodeEditText.text.toString().isEmpty()) {
            this.errorZipCodeTextView.visibility = View.VISIBLE
            errors += 1
        }
        return errors
    }

    private fun clearError() {
        this.errorFirstnameTextView.visibility = View.INVISIBLE
        this.errorLastnameTextView.visibility = View.INVISIBLE
        //this.errorPasswordTextView.visibility = View.INVISIBLE
        this.errorEmailTextView.visibility = View.INVISIBLE
        this.errorCityTextView.visibility = View.INVISIBLE
        this.errorZipCodeTextView.visibility = View.INVISIBLE
        this.errorAddressTextView.visibility = View.INVISIBLE
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
        return emailRegex.matches(email)
    }

    private fun isFrenchPostalCode(postalCode: String): Boolean {
        val postalCodeRegex = Regex("^\\d{5}$")
        return postalCode.matches(postalCodeRegex)
    }
}