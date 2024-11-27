package com.amc.acieslinski.simplegiftapp.data.datasource.user.mapper

import com.amc.acieslinski.simplegiftapp.data.datasource.user.model.UserRemoteModel
import com.amc.acieslinski.simplegiftapp.data.datasource.user.model.GetUserResponseModel

class UserRemoteMapper {
    fun mapToUser(userRemoteModel: UserRemoteModel) = GetUserResponseModel(
        userRemoteModel.name,
        userRemoteModel.surname,
        userRemoteModel.idToken,
    )
}