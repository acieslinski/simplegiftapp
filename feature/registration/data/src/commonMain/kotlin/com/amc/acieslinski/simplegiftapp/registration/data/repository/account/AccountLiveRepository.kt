package com.amc.acieslinski.simplegiftapp.registration.data.repository.account

import com.amc.acieslinski.simplegiftapp.data.repository.Repository
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.AccountLocalDataSource
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.AccountRemoteDataSource
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.model.AccountDatabaseModel
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.model.AccountResponseModel
import com.amc.acieslinski.simplegiftapp.registration.domain.model.RegisterAccountResult
import com.amc.acieslinski.simplegiftapp.registration.domain.repositories.AccountRepository

class AccountLiveRepository(
    private val accountRemoteDataSource: AccountRemoteDataSource,
    private val accountLocalDataSource: AccountLocalDataSource,
) : AccountRepository, Repository() {
    override suspend fun register(name: String, surname: String): RegisterAccountResult =
        tryCatching(
            action = {
                val accountResponseModel = accountRemoteDataSource.register(name, surname)
                accountLocalDataSource.save(accountResponseModel.toDatabaseModel())
                RegisterAccountResult.Success
            },
            error = { RegisterAccountResult.UnknownFailure }
        )

    override suspend fun isRegistered(): Boolean = accountLocalDataSource.getAccount() != null
}

fun AccountResponseModel.toDatabaseModel() = AccountDatabaseModel(
    name = name,
    surname = surname,
    public = public,
    private = private,
)