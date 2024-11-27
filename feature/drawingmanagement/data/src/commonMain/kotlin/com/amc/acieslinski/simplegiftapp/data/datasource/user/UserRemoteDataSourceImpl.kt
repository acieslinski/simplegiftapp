package com.amc.acieslinski.simplegiftapp.data.datasource.user

import com.amc.acieslinski.simplegiftapp.API_KEY
import com.amc.acieslinski.simplegiftapp.URL
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.RequestException
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.withHandlingUnexpectedResponseStatus
import com.amc.acieslinski.simplegiftapp.data.datasource.user.mapper.UserRemoteMapper
import com.amc.acieslinski.simplegiftapp.data.datasource.user.model.UserRemoteModel
import com.amc.acieslinski.simplegiftapp.data.datasource.user.model.GetUserResponseModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRemoteDataSourceImpl(
    private val httpClient: HttpClient,
    private val userRemoteMapper: UserRemoteMapper,
) : UserRemoteDataSource {
    @Throws(RequestException::class)
    override fun getUser(idToken: String): Flow<GetUserResponseModel> {
        return flow {
            httpClient.get("$URL/user?idToken=$idToken&token=$API_KEY")
                .withHandlingUnexpectedResponseStatus {
                    val user = body<UserRemoteModel>()
                    emit(userRemoteMapper.mapToUser(user))
                }
            // TODO check network availability
        }
    }
}