package com.amc.acieslinski.simplegiftapp.data.datasource.token

interface PrivateTokenDataSource {
    suspend fun getPrivateToken(): String
}