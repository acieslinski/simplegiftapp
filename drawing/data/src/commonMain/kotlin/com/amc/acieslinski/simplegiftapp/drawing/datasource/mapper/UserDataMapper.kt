package com.amc.acieslinski.simplegiftapp.drawing.datasource.mapper

import com.amc.acieslinski.simplegiftapp.drawing.datasource.model.UserRemote
import com.amc.acieslinski.simplegiftapp.drawing.repository.UserData

class UserDataMapper {
    fun mapToUser(userRemote: UserRemote) = UserData(
        userRemote.name,
        userRemote.surname,
        userRemote.idToken,
    )
}