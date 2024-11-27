package com.amc.acieslinski.simplegiftapp.drawingmanagement.domain

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.UserResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(idToken: String): Flow<UserResult> = repository.getUser(idToken)
}