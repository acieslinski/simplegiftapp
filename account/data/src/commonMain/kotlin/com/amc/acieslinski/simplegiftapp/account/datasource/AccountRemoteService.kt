package com.amc.acieslinski.simplegiftapp.account.datasource

import com.amc.acieslinski.simplegiftapp.account.datasource.exception.RequestException
import com.amc.acieslinski.simplegiftapp.account.datasource.mapper.AccountDataMapper
import com.amc.acieslinski.simplegiftapp.account.datasource.model.AccountRemote
import com.amc.acieslinski.simplegiftapp.account.repository.AccountData
import com.amc.acieslinski.simplegiftapp.account.repository.AccountRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AccountRemoteService(
    private val httpClient: HttpClient,
    private val accountDataMapper: AccountDataMapper
) : AccountRemoteDataSource {

    override fun register(name: String, surname: String): Flow<AccountData> {
        return flow {
            val response = httpClient.get("$URL/register?name=$name&surname=$surname&token=$API_KEY")
            if (response.status.value in 200..299) {
                val account = response.body<AccountRemote>()
                emit(accountDataMapper.mapToAccount(account))
            } else {
                throw RequestException(response.status)
            }
            // TODO check network availability
        }
    }
}