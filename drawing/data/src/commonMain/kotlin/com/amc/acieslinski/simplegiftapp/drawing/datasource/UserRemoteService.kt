package com.amc.acieslinski.simplegiftapp.drawing.datasource

import com.amc.acieslinski.simplegiftapp.drawing.datasource.exception.RequestException
import com.amc.acieslinski.simplegiftapp.drawing.datasource.mapper.UserDataMapper
import com.amc.acieslinski.simplegiftapp.drawing.datasource.model.UserRemote
import com.amc.acieslinski.simplegiftapp.drawing.repository.UserData
import com.amc.acieslinski.simplegiftapp.drawing.repository.UserRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRemoteService(
    private val httpClient: HttpClient,
    private val userDataMapper: UserDataMapper
) : UserRemoteDataSource {
    override fun getUser(idToken: String): Flow<UserData> {
        return flow {
            val response = httpClient.get("$URL/user?idToken=$idToken&token=$API_KEY")
            if (response.status.value in 200..299) {
                val user = response.body<UserRemote>()
                emit(userDataMapper.mapToUser(user))
            } else {
                throw RequestException(response.status)
            }
            // TODO check network availability
        }
    }
}