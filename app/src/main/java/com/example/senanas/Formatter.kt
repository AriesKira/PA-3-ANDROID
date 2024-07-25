package com.example.senanas

import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class Formatter {
    companion object {
        fun formatDate(dateString: String): String {
            val zonedDateTime = ZonedDateTime.parse(dateString)
            val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, HH:mm", Locale.FRENCH)
            return zonedDateTime.format(formatter)
        }
    }

}