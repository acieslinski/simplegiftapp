package com.amc.acieslinski.simplegiftapp.dashboard.data.datasource.drawing

import com.amc.acieslinski.simplegiftapp.API_KEY
import com.amc.acieslinski.simplegiftapp.URL
import com.amc.acieslinski.simplegiftapp.dashboard.data.datasource.drawing.model.DrawingRemoteModel
import com.amc.acieslinski.simplegiftapp.dashboard.data.datasource.drawing.model.GetDrawingsRequestModel
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.RequestException
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.withHandlingUnexpectedResponseStatus
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlin.coroutines.cancellation.CancellationException

class DrawingRemoteDataSourceImpl(
    private val httpClient: HttpClient,
) : DrawingRemoteDataSource {
    @Throws(RequestException::class, CancellationException::class)
    override suspend fun getDrawings(request: GetDrawingsRequestModel): List<DrawingRemoteModel> {
        return httpClient
            .get("$URL/drawings?token=$API_KEY") {
                contentType(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer ${request.privateToken}")
                }
            }
            .withHandlingUnexpectedResponseStatus()
            .body<List<DrawingRemoteModel>>()
        // TODO check network availability
    }
}