package com.amc.acieslinski.simplegiftapp.data.datasource.user

import com.amc.acieslinski.simplegiftapp.data.datasource.exception.RequestException
import com.amc.acieslinski.simplegiftapp.data.datasource.user.model.GetUserResponseModel
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    @Throws(RequestException::class)
    fun getUser(idToken: String): Flow<GetUserResponseModel>
}