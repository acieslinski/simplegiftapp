package com.amc.acieslinski.simplegiftapp.account.repository

interface AccountLocalDataSource {
    fun save(account: AccountData)
}