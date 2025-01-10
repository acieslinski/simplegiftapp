package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.user

import com.amc.acieslinski.simplegiftapp.API_KEY
import com.amc.acieslinski.simplegiftapp.URL
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.RequestException
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.withHandlingUnexpectedResponseStatus
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.user.model.UserResponseModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlin.coroutines.cancellation.CancellationException

class UserRemoteLiveDataSource(
    private val httpClient: HttpClient,
) : UserRemoteDataSource {
    @Throws(RequestException::class, CancellationException::class)
    override suspend fun getUser(userId: String): UserResponseModel {
        return httpClient
            .get("$URL/user?idToken=$userId&token=$API_KEY")
            .withHandlingUnexpectedResponseStatus()
            .body<UserResponseModel>()
        // TODO check network availability
    }
}