package com.example.senanas

import org.junit.Test
import org.junit.Assert.*
import java.time.LocalDateTime

class FormatterUnitTest {
    @Test
    fun testFormatDate() {
        val dateString = "2024-07-25T14:30:00+02:00"
        assertEquals("25 juillet 2024, 14:30", Formatter.formatDate(dateString))
    }

    @Test
    fun testFormatDateWithDifferentDate() {
        val dateString = "2023-12-01T09:45:00+01:00"
        assertEquals("1 décembre 2023, 09:45", Formatter.formatDate(dateString))
    }
}