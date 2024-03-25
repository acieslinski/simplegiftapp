package com.amc.acieslinski.simplegiftapp.drawing.datasource.exception

import io.ktor.http.HttpStatusCode

class RequestException(
    val statusCode: HttpStatusCode
) : Exception()