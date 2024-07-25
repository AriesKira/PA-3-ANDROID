package com.example.senanas

import org.junit.Test
import org.junit.Assert.*

class ValidatorUnitTest {

    @Test
    fun testValidEmails() {
        assertTrue(Validator.isValidEmail("test@example.com"))
        assertTrue(Validator.isValidEmail("user.name+tag+sorting@example.com"))
    }

    @Test
    fun testInvalidEmails() {
        assertFalse(Validator.isValidEmail("plainaddress"))
        assertFalse(Validator.isValidEmail("@missingusername.com"))
    }

    @Test
    fun testValidFrenchPostalCodes() {
        assertTrue(Validator.isFrenchPostalCode("75001"))
        assertTrue(Validator.isFrenchPostalCode("13000"))
        assertTrue(Validator.isFrenchPostalCode("69008"))
    }

    @Test
    fun testInvalidFrenchPostalCodes() {
        assertFalse(Validator.isFrenchPostalCode("7500"))
        assertFalse(Validator.isFrenchPostalCode("750001"))
        assertFalse(Validator.isFrenchPostalCode("75A01"))
        assertFalse(Validator.isFrenchPostalCode("750 01"))
        assertFalse(Validator.isFrenchPostalCode("750-01"))
    }

    @Test
    fun testValidPhoneNumbers() {
        assertTrue(Validator.isValidPhoneNumber("0123456789"))
        assertTrue(Validator.isValidPhoneNumber("0712345678"))
        assertTrue(Validator.isValidPhoneNumber("0612345678"))
    }

    @Test
    fun testInvalidPhoneNumbers() {
        assertFalse(Validator.isValidPhoneNumber("123456789"))
        assertFalse(Validator.isValidPhoneNumber("00123456789"))
        assertFalse(Validator.isValidPhoneNumber("A612345678"))
        assertFalse(Validator.isValidPhoneNumber("06 12 34 56 78"))
        assertFalse(Validator.isValidPhoneNumber("061-234-5678"))
    }


}