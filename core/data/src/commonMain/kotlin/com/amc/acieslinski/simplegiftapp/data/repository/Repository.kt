package com.amc.acieslinski.simplegiftapp.data.repository

import kotlin.coroutines.cancellation.CancellationException

open class Repository {
    protected inline fun <T, R> T.tryCatching(block: T.() -> R): Result<R> {
        return try {
            Result.success(block())
        } catch (e: CancellationException) {
            throw e
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}