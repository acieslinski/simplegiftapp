package com.amc.acieslinski.simplegiftapp.account.repository

import com.amc.acieslinski.simplegiftapp.Log
import com.amc.acieslinski.simplegiftapp.account.domain.model.RegisterAccountResult
import com.amc.acieslinski.simplegiftapp.account.domain.repositories.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

private val TAG = AccountRepository::class.simpleName!!
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
                Log.e(it) { "registration exception" }
                emit(RegisterAccountResult.UnknownIssue)
            }
}