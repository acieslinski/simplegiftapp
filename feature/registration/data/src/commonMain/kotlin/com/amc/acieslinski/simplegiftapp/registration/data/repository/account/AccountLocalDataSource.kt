package com.amc.acieslinski.simplegiftapp.registration.data.repository.account

interface AccountLocalDataSource {
    fun save(account: AccountData)

    suspend fun getAccount(): AccountData?
}