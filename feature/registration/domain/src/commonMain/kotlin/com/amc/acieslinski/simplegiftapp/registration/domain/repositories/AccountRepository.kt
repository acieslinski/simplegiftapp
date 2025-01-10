package com.amc.acieslinski.simplegiftapp.registration.domain.repositories

import com.amc.acieslinski.simplegiftapp.registration.domain.model.RegisterAccountResult
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun register(name: String, surname: String): RegisterAccountResult

    suspend fun isRegistered(): Boolean
}

