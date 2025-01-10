package com.amc.acieslinski.simplegiftapp.registration.data.datasource.account

import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.model.AccountResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class AccountRemoteFakeDataSource(
) : AccountRemoteDataSource {

    override suspend fun register(name: String, surname: String): AccountResponseModel {
        return withContext(Dispatchers.IO) {
            delay(3000)
            AccountResponseModel(
                name = name,
                surname = surname,
                public = "public",
                private = "private"
            )
        }
    }
}