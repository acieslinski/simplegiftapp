package com.amc.acieslinski.simplegiftapp.data.datasource.exception

import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
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
        throw status.toRequestException()
    }

    return this
}

@Throws(RequestException::class)
fun HttpResponse.withHandlingUnexpectedResponseStatus(): HttpResponse {
    if (status.value !in 200..299) {
        throw status.toRequestException()
    }
    return this
}

private fun HttpStatusCode.toRequestException(): RequestException = when(this) {
    HttpStatusCode.NotFound -> RequestException.NotFoundException()
    else -> RequestException.UnknownException()
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