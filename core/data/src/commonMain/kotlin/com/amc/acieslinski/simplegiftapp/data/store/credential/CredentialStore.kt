package com.amc.acieslinski.simplegiftapp.data.store.credential

interface CredentialStore {
    suspend fun getPrivateToken(): String
}