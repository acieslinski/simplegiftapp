package com.amc.acieslinski.simplegiftapp.drawing.repository

import com.amc.acieslinski.simplegiftapp.drawing.domain.model.User
import com.amc.acieslinski.simplegiftapp.drawing.domain.model.UserResult
import com.amc.acieslinski.simplegiftapp.drawing.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UserFakeRepositoryImpl : UserRepository {
    override fun getUser(idToken: String): Flow<UserResult> {
        return flowOf(
            UserResult.Success(
                User(
                    "test name",
                    "test surname",
                    idToken
                )
            )
        )
    }
}