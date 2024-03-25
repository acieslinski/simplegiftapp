package com.amc.acieslinski.simplegiftapp.drawing.domain.repositories

import com.amc.acieslinski.simplegiftapp.drawing.domain.model.UserResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(idToken: String): Flow<UserResult>
}

