package com.amc.acieslinski.simplegiftapp.dashboard.presentation.model

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.dateWithTimeIntervalSince1970

actual fun DrawingUiState.getFormattedDate(): String {
    val date = NSDate.dateWithTimeIntervalSince1970(date.epochSeconds.toDouble())
    val formatter = NSDateFormatter().apply {
        dateFormat = "dd.MM.yyyy"
        locale = NSLocale.currentLocale
    }
    return formatter.stringFromDate(date)
}