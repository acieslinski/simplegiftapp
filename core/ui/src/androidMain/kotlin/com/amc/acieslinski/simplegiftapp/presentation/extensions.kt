package com.amc.acieslinski.simplegiftapp.presentation

import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import java.time.ZoneId
import java.time.format.FormatStyle
import java.util.Locale

actual fun Instant.getShortFormattedDate(): String {
    val javaInstant = this.toJavaInstant()
    val formatter = java.time.format.DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(
        Locale.getDefault())
    return javaInstant.atZone(ZoneId.systemDefault()).toLocalDate().format(formatter)
}