package com.compose.newsapplite.utils

import java.lang.StringBuilder
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter

fun LocalDate.convertToNewsAppLiteDate(): String {
    val sb = StringBuilder()
    sb.append("${this.month.convertToCamelCase()} ${this.dayOfMonth}, ${this.year}")

    return sb.toString()
}

fun Month.convertToCamelCase(): String {
    return this.name
        .mapIndexed { index, char ->
            val moddedChar = if (index == 0) char.toUpperCase() else char.toLowerCase()

            moddedChar
        }
        .toString()
        .filterNot {
            it == '[' || it == ']' || it == ',' || it == ' '
        }
}

fun String.toFormattedDateString(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val localDate = LocalDate.parse(this, formatter)

    return localDate.convertToNewsAppLiteDate()
}