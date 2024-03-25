package com.amc.acieslinski.simplegiftapp

import co.touchlab.kermit.Logger
import co.touchlab.kermit.loggerConfigInit

object Log {
    private val logger = Logger(
        config = loggerConfigInit(
            minSeverity = LogConfig.severity,
        )
    )

    fun e(e: Throwable, message: () -> String) {
        Logger.withTag(guessTag()).e(e, message = message)
    }

    fun d(message: String) {
        Logger.withTag(guessTag()).d { message }
    }

    fun i(message: String) {
        logger.withTag(guessTag()).i { message }
    }

    private fun guessTag(): String {
        val stackTraceString = Throwable().stackTraceToString()
        return stackTraceString.lineSequence()
            .map { line ->
                line.substringAfter("at ")
                    .substringBefore('(')
                    .substringBeforeLast('.')
            }
            .firstOrNull {
                it.contains("com.amc.acieslinski.simplegiftapp") &&
                        !it.endsWith("Log")
            }
            ?.substringAfterLast('.')
            ?: "GIFT_APP"
    }
}