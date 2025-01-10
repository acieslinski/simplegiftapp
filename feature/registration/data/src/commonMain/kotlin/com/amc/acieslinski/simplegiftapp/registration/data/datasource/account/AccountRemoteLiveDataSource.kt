package com.amc.acieslinski.simplegiftapp.registration.data.datasource.account

import com.amc.acieslinski.simplegiftapp.API_KEY
import com.amc.acieslinski.simplegiftapp.URL
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.model.AccountResponseModel
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.withHandlingUnexpectedResponseStatus
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class AccountRemoteLiveDataSource(
    private val httpClient: HttpClient,
) : AccountRemoteDataSource {

    override suspend fun register(name: String, surname: String): AccountResponseModel {
        return httpClient.get("$URL/register?name=$name&surname=$surname&token=$API_KEY")
            .withHandlingUnexpectedResponseStatus()
            .body<AccountResponseModel>()
        // TODO check network availability
    }
}