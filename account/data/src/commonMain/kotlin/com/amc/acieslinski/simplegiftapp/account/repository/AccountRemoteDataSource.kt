package com.amc.acieslinski.simplegiftapp.account.repository

import kotlinx.coroutines.flow.Flow

interface AccountRemoteDataSource {
    fun register(name: String, surname: String): Flow<AccountData>
}