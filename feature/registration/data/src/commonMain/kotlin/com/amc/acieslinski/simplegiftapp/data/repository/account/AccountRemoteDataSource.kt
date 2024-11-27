package com.amc.acieslinski.simplegiftapp.data.repository.account

import kotlinx.coroutines.flow.Flow

interface AccountRemoteDataSource {
    fun register(name: String, surname: String): Flow<AccountData>
}