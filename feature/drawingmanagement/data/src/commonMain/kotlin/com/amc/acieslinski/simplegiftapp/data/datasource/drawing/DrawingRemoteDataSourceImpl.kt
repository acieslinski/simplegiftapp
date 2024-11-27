package com.amc.acieslinski.simplegiftapp.data.datasource.drawing

import com.amc.acieslinski.simplegiftapp.API_KEY
import com.amc.acieslinski.simplegiftapp.URL
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.mapper.DrawingRemoteMapper
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.RequestException
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.model.CreateDrawingRequestModel
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.withHandlingUnexpectedResponseStatus
import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlin.coroutines.cancellation.CancellationException

class DrawingRemoteDataSourceImpl(
    private val httpClient: HttpClient,
    private val drawingRemoteMapper: DrawingRemoteMapper,
) : DrawingRemoteDataSource {
    @Throws(RequestException::class, CancellationException::class)
    override suspend fun createDrawing(drawing: CreateDrawingRequestModel) {
        httpClient
            .post("$URL/drawing?token=$API_KEY") {
                contentType(ContentType.Application.Json)
                setBody(drawingRemoteMapper.toRemote(drawing))
                headers {
                    append("Authorization", "Bearer ${drawing.privateToken}")
                }
            }
            .withHandlingUnexpectedResponseStatus()
        // TODO check network availability
    }
}