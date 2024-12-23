package com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.user.mappers

import com.amc.acieslinski.simplegiftapp.data.datasource.exception.RequestException
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.User
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.model.UserResult
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.user.model.GetUserResponseModel

class UserDataMapper {
    fun toData(user: User) = with(user) {
        GetUserResponseModel(
            name = name,
            surname = surname,
            idToken = idToken,
        )
    }

    fun toDomain(getUserResponseModel: GetUserResponseModel): UserResult =
        with(getUserResponseModel) {
            UserResult.Success(
                User(
                    name = name,
                    surname = surname,
                    idToken = idToken
                )
            )
        }

    fun mapRequestException(exception: RequestException) = when (exception) {
        is RequestException.NotFoundException -> UserResult.NotFound
        is RequestException.UnknownException -> UserResult.UnknownIssue
    }
}