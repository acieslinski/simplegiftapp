package com.amc.acieslinski.simplegiftapp.account.datasource.exception

import io.ktor.http.HttpStatusCode

class RequestException(
    val statusCode: HttpStatusCode
) : Exception()