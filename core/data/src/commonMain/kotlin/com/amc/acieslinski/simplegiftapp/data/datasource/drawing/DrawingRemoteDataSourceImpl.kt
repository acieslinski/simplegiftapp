package com.amc.acieslinski.simplegiftapp.data.datasource.drawing

import com.amc.acieslinski.simplegiftapp.API_KEY
import com.amc.acieslinski.simplegiftapp.URL
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.DrawingRemoteModel
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.GetDrawingsRequestModel
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
    private var selectedDrawingId: String? = null

    @Throws(RequestException::class, CancellationException::class)
    override suspend fun getDrawings(request: GetDrawingsRequestModel): List<DrawingRemoteModel> {
        return getDrawingsInternal(request.privateToken)
    }

    override suspend fun selectDrawing(drawingId: String) {
        selectedDrawingId = drawingId
    }

    override suspend fun getSelectedDrawing(request: GetDrawingsRequestModel): DrawingRemoteModel? {
        return selectedDrawingId?.let {
            getDrawingsInternal(request.privateToken)
                .first { it.id == selectedDrawingId }
        }
    }

    @Throws(RequestException::class, CancellationException::class)
    private suspend fun getDrawingsInternal(privateToken: String): List<DrawingRemoteModel> {
        return httpClient
            .get("$URL/drawings?token=$API_KEY") {
                contentType(ContentType.Application.Json)
                headers {
                    append("Authorization", "Bearer $privateToken")
                }
            }
            .withHandlingUnexpectedResponseStatus()
            .body<List<DrawingRemoteModel>>()
        // TODO check network availability
    }
}