package com.amc.acieslinski.simplegiftapp.data.datasource.account

import com.amc.acieslinski.simplegiftapp.data.datasource.account.model.AccountDatabaseModel

interface AccountLocalDataSource {
    suspend fun save(account: AccountDatabaseModel)

    suspend fun getAccount(): AccountDatabaseModel?
}