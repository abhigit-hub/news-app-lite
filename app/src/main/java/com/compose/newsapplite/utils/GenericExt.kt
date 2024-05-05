package com.compose.newsapplite.utils

import java.lang.StringBuilder
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId
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

fun String.toPublishedAtString(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val localDateTimeForPublishedAt = LocalDateTime.parse(this, formatter)

    val displayStringBuilder = StringBuilder("")

    val epochTimeCurrent = System.currentTimeMillis()
    val epochTimePublishedAt = localDateTimeForPublishedAt.atZone(ZoneId.systemDefault()).toEpochSecond()
    val timeDifferenceInSeconds = (epochTimeCurrent / 1000) - epochTimePublishedAt

    val diffInMinutes = (timeDifferenceInSeconds / (60)).toInt()
    val diffInHours = (timeDifferenceInSeconds / (60 * 60)).toInt()
    val diffInDays = (timeDifferenceInSeconds / (60 * 60 * 24)).toInt()
    val diffInMonths = (timeDifferenceInSeconds / (60 * 60 * 24 * 30)).toInt()

    if (diffInMonths > 0) {
        displayStringBuilder
            .append(diffInMonths)
            .append(if (diffInMonths == 1) " month " else " months ")
            .append("ago")
    } else if (diffInDays > 0) {
        displayStringBuilder
            .append(diffInDays)
            .append(if (diffInDays == 1) " day " else " days ")
            .append("ago")
    } else if (diffInHours > 0) {
        displayStringBuilder
            .append(diffInHours)
            .append(if (diffInHours == 1) " hour " else " hours ")
            .append("ago")
    } else if (diffInMinutes > 0) {
        displayStringBuilder
            .append(diffInMinutes)
            .append(if (diffInMinutes == 1) " min " else " mins ")
            .append("ago")
    }

    return displayStringBuilder.toString()
}

