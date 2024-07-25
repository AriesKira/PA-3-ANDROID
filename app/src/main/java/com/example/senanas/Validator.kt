package com.example.senanas

class Validator {
    companion object {
        fun isValidEmail(email: String): Boolean {
            val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
            return emailRegex.matches(email)
        }

        fun isFrenchPostalCode(postalCode: String): Boolean {
            val postalCodeRegex = Regex("^\\d{5}$")
            return postalCode.matches(postalCodeRegex)
        }

        fun isValidPhoneNumber(phoneNumber: String): Boolean {
            val phoneNumberRegex = Regex("^0[1-9]\\d{8}$")
            return phoneNumber.matches(phoneNumberRegex)
        }



    }
}