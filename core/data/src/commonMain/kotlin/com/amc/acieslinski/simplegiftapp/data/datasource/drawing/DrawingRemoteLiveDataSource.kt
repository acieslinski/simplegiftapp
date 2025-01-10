package com.amc.acieslinski.simplegiftapp.data.datasource.drawing

import com.amc.acieslinski.simplegiftapp.API_KEY
import com.amc.acieslinski.simplegiftapp.URL
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.DrawingRequestModel
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.DrawingResponseModel
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.RequestException
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.withHandlingUnexpectedResponseStatus
import com.amc.acieslinski.simplegiftapp.data.store.credential.CredentialStore
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlin.coroutines.cancellation.CancellationException

class DrawingRemoteLiveDataSource(
    private val httpClient: HttpClient,
    private val privateTokenStore: CredentialStore,
) : DrawingRemoteDataSource {
    @Throws(RequestException::class, CancellationException::class)
    override suspend fun getDrawings(): List<DrawingResponseModel> {
        val privateToken = privateTokenStore.getPrivateToken()
        return httpClient
            .get("$URL/drawings?token=$API_KEY") {
                contentType(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $privateToken")
                }
            }
            .withHandlingUnexpectedResponseStatus()
            .body<List<DrawingResponseModel>>()
        // TODO check network availability
    }

    @Throws(RequestException::class, CancellationException::class)
    override suspend fun saveDrawing(drawing: DrawingRequestModel) {
        val privateToken = privateTokenStore.getPrivateToken()
        httpClient
            .post("$URL/drawing?token=$API_KEY") {
                contentType(ContentType.Application.Json)
                setBody(drawing)
                headers {
                    append("Authorization", "Bearer $privateToken")
                }
            }
            .withHandlingUnexpectedResponseStatus()
        // TODO check network availability
    }
}