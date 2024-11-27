package com.amc.acieslinski.simplegiftapp.data.repository.user

import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.User
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.UserResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.UserRepository
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