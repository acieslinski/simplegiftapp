package com.amc.acieslinski.simplegiftapp.registration.data.datasource.account

import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.model.AccountResponseModel

interface AccountRemoteDataSource {
    suspend fun register(name: String, surname: String): AccountResponseModel
}