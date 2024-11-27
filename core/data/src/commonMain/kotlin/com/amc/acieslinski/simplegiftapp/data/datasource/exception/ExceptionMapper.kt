package com.amc.acieslinski.simplegiftapp.data.datasource.exception

import io.ktor.http.HttpStatusCode

object ExceptionMapper {
    fun mapStatusCode(statusCode: HttpStatusCode): RequestException = when (statusCode) {
        HttpStatusCode.NotFound -> RequestException.NotFoundException()
        else -> RequestException.UnknownException()
    }
}
