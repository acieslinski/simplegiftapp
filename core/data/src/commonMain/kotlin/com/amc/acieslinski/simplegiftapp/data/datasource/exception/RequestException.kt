package com.amc.acieslinski.simplegiftapp.data.datasource.exception

sealed class RequestException(
    val statusCode: Int
) : Exception() {
    class NotFoundException : RequestException(404)
    class UnknownException : RequestException(-1)
}