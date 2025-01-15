package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.drawing

import com.amc.acieslinski.simplegiftapp.API_KEY
import com.amc.acieslinski.simplegiftapp.URL
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.withHandlingUnexpectedResponseStatus
import com.amc.acieslinski.simplegiftapp.data.store.credential.CredentialStore
import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType

class DrawingDrawRemoteLiveDataSource(
    private val httpClient: HttpClient,
    private val privateTokenStore: CredentialStore,
): DrawingDrawRemoteDataSource {
    override suspend fun drawParticipants(drawingId: String) {
        val privateToken = privateTokenStore.getPrivateToken()
        httpClient
            .post("$URL/draw/${drawingId}?token=$API_KEY") {
                contentType(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $privateToken")
                }
            }
            .withHandlingUnexpectedResponseStatus()
        // TODO check network availability
    }
}