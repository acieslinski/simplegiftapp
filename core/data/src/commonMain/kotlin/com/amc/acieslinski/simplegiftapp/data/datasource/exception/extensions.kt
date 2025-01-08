package com.amc.acieslinski.simplegiftapp.data.datasource.exception

import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlin.coroutines.cancellation.CancellationException

@Throws(RequestException::class, CancellationException::class)
suspend fun HttpResponse.withHandlingUnexpectedResponseStatus(
    block: suspend HttpResponse.() -> Unit
): HttpResponse {
    if (status.value in 200..299) {
        block()
    } else {
        throw ExceptionMapper.mapStatusCode(status)
    }

    return this
}

@Throws(RequestException::class)
fun HttpResponse.withHandlingUnexpectedResponseStatus(): HttpResponse {
    if (status.value !in 200..299) {
        throw ExceptionMapper.mapStatusCode(status)
    }
    return this
}

inline fun <T> Flow<T>.catchRequestException(
    noinline block: suspend FlowCollector<T>.(exception: RequestException) -> Unit
) =
    catch {
        if (it is RequestException) {
            block(it)
        } else {
            throw it
        }
    }

@Deprecated("use sealed classes as results")
inline fun <T, R> T.withHandlingRepositoryExceptions(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        Result.failure(e)
    }
}

@Deprecated("use sealed classes as results")
inline fun <T> T.withHandlingRepositoryExceptions(block: T.() -> Unit): Result<Unit> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        Result.failure(e)
    }
}