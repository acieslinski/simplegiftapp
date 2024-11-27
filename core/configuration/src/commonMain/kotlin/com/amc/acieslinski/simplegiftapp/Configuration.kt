package com.amc.acieslinski.simplegiftapp

val configuration = Configuration()
data class Configuration(
    val useFakeAccount: Boolean = true,
    val useFakeAccountLocalDataSource: Boolean = true,
    val useFakeAccountRemoteDataSource: Boolean = true,
    val useFakeDrawingUserRepository: Boolean = true,
    val useFakeDrawingRepository: Boolean = false,
    val useFakePrivateTokenLocalDataSource: Boolean = true,
)