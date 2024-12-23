package com.amc.acieslinski.simplegiftapp.registration.data.repository.account

import kotlinx.coroutines.flow.Flow

interface AccountRemoteDataSource {
    fun register(name: String, surname: String): Flow<AccountData>
}