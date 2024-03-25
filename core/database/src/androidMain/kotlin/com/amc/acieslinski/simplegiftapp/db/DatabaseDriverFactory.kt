package com.amc.acieslinski.simplegiftapp.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class DatabaseDriverFactory(private val context: Context) {

    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = SimpleGiftAppDatabase.Schema,
            context = context,
            name = "DailyPulse.Database.db"
        )
}