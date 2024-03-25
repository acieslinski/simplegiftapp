package com.amc.acieslinski.simplegiftapp.drawing.repository

import com.amc.acieslinski.simplegiftapp.Log
import com.amc.acieslinski.simplegiftapp.drawing.datasource.exception.RequestException
import com.amc.acieslinski.simplegiftapp.drawing.domain.model.User
import com.amc.acieslinski.simplegiftapp.drawing.domain.model.UserResult
import com.amc.acieslinski.simplegiftapp.drawing.domain.repositories.UserRepository
import io.ktor.http.HttpStatusCode.Companion.NotFound
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override fun getUser(idToken: String): Flow<UserResult> {
        return userRemoteDataSource.getUser(idToken)
            .map {
                it.toDomain()
            }
            .catch {
                if (it is RequestException && it.statusCode == NotFound) {
                    emit(UserResult.NotFound)
                } else {
                    Log.e(it) { "exception: " }
                    emit(UserResult.UnknownIssue)
                }
            }
    }

    private fun UserData.toDomain(): UserResult = UserResult.Success(
        User(
            name = name,
            surname = surname,
            idToken = idToken
        )
    )
}