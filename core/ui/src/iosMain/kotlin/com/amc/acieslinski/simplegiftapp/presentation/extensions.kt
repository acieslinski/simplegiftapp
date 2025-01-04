package com.amc.acieslinski.simplegiftapp.presentation

import kotlinx.datetime.Instant
import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.dateWithTimeIntervalSince1970

actual fun Instant.getShortFormattedDate(): String {
    val date = NSDate.dateWithTimeIntervalSince1970(epochSeconds.toDouble())
    val formatter = NSDateFormatter().apply {
        dateFormat = "dd.MM.yyyy"
        locale = NSLocale.currentLocale
    }
    return formatter.stringFromDate(date)
}