package com.amc.acieslinski.simplegiftapp.account.domain.repositories

import com.amc.acieslinski.simplegiftapp.account.domain.model.RegisterAccountResult
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun register(name: String, surname: String): Flow<RegisterAccountResult>
}

