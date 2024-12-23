package com.amc.acieslinski.simplegiftapp.dashboard.presentation.model

import kotlinx.datetime.toJavaInstant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

actual fun DrawingUiState.getFormattedDate(): String {
    val javaInstant = date.toJavaInstant()
    val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.getDefault())
    return javaInstant.atZone(ZoneId.systemDefault()).toLocalDate().format(formatter)
}