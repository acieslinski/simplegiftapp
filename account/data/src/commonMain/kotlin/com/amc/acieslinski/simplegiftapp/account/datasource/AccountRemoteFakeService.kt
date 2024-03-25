package com.amc.acieslinski.simplegiftapp.account.datasource

import com.amc.acieslinski.simplegiftapp.account.repository.AccountData
import com.amc.acieslinski.simplegiftapp.account.repository.AccountRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AccountRemoteFakeService(
) : AccountRemoteDataSource {

    override fun register(name: String, surname: String): Flow<AccountData> {
        return flow {
            delay(3000)
            emit(
                AccountData(
                name,
                surname,
                "public",
                "private"
            )
            )
        }.flowOn(Dispatchers.IO)
    }
}