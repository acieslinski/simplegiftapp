package com.amc.acieslinski.simplegiftapp.db

import app.cash.sqldelight.db.SqlDriver
import org.koin.dsl.module

actual val databaseModule = module {

    single<SqlDriver> { DatabaseDriverFactory().createDriver() }

    single<SimpleGiftAppDatabase> { SimpleGiftAppDatabase(get()) }
}