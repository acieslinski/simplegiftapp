package com.amc.acieslinski.simplegiftapp.data.repository

import com.amc.acieslinski.simplegiftapp.data.datasource.exception.RequestException
import kotlin.coroutines.cancellation.CancellationException

open class Repository {
    protected inline fun <T, R> T.tryCatching(
        action: T.() -> R,
        noinline error: T.(e: Throwable) -> R,
        noinline requestFailure: (T.(e: RequestException) -> R) = error,
    ): R {
        return try {
            action()
        } catch (e: CancellationException) {
            throw e
        } catch (e: RequestException) {
            requestFailure(e)
        } catch (e: Throwable) {
            error(e)
        }
    }
}