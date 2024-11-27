package com.amc.acieslinski.simplegiftapp.data.repository.user

import com.amc.acieslinski.simplegiftapp.Log
import com.amc.acieslinski.simplegiftapp.data.datasource.exception.catchRequestException
import com.amc.acieslinski.simplegiftapp.data.datasource.user.UserRemoteDataSource
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.UserResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.UserRepository
import com.amc.acieslinski.simplegiftapp.data.repository.user.mappers.UserDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userDataMapper: UserDataMapper,
) : UserRepository {
    override fun getUser(idToken: String): Flow<UserResult> {
        return userRemoteDataSource.getUser(idToken)
            .map {
                userDataMapper.toDomain(it)
            }
            .catchRequestException {
                userDataMapper.mapRequestException(it).run { emit(this) }
            }
            .catch {
                Log.e(it) { "exception: " }
                emit(UserResult.UnknownIssue)
            }
    }
}