package com.amc.acieslinski.simplegiftapp.registration.data.datasource.account

import com.amc.acieslinski.simplegiftapp.API_KEY
import com.amc.acieslinski.simplegiftapp.URL
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.RequestException
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.mapper.AccountDataMapper
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.model.AccountRemote
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.withHandlingUnexpectedResponseStatus
import com.amc.acieslinski.simplegiftapp.registration.data.repository.account.AccountData
import com.amc.acieslinski.simplegiftapp.registration.data.repository.account.AccountRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AccountRemoteDataSourceImpl(
    private val httpClient: HttpClient,
    private val accountDataMapper: AccountDataMapper
) : AccountRemoteDataSource {

    override fun register(name: String, surname: String): Flow<AccountData> {
        return flow {
            httpClient.get("$URL/register?name=$name&surname=$surname&token=$API_KEY")
                .withHandlingUnexpectedResponseStatus {
                    val account = body<AccountRemote>()
                    emit(accountDataMapper.mapToAccount(account))
                }
            // TODO check network availability
        }
    }
}