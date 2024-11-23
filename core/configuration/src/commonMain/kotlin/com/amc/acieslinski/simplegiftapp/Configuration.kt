package com.amc.acieslinski.simplegiftapp

val configuration = Configuration()
data class Configuration(
    val useFakeAccount: Boolean = true,
    val useFakeAccountLocalService: Boolean = true,
    val useFakeAccountRemoteService: Boolean = true,
    val useFakeDrawingUserRepository: Boolean = true,
)