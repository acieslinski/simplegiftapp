package com.amc.acieslinski.simplegiftapp.data.repository.account

import com.amc.acieslinski.simplegiftapp.Log
import com.amc.acieslinski.simplegiftapp.registration.domain.model.RegisterAccountResult
import com.amc.acieslinski.simplegiftapp.registration.domain.repositories.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class AccountRepositoryImpl(
    private val accountRemoteDataSource: AccountRemoteDataSource,
    private val accountLocalDataSource: AccountLocalDataSource,
) : AccountRepository {
    override fun register(name: String, surname: String): Flow<RegisterAccountResult> =
        accountRemoteDataSource.register(name, surname)
            .onEach {
                accountLocalDataSource.save(it)
            }
            .map {
                @Suppress("USELESS_CAST")
                RegisterAccountResult.Success as RegisterAccountResult
            }
            .catch {
                // TODO handle request exceptions
                Log.e(it) { "registration exception" }
                emit(RegisterAccountResult.UnknownIssue)
            }

    override suspend fun isRegistered(): Boolean = accountLocalDataSource.getAccount() != null
}