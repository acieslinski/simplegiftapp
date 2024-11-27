package com.amc.acieslinski.simplegiftapp.data.datasource.account

import com.amc.acieslinski.simplegiftapp.data.repository.account.AccountData
import com.amc.acieslinski.simplegiftapp.data.repository.account.AccountRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AccountRemoteFakeDataSource(
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