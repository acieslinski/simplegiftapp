package com.amc.acieslinski.simplegiftapp.drawing.repository

import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    fun getUser(idToken: String): Flow<UserData>
}