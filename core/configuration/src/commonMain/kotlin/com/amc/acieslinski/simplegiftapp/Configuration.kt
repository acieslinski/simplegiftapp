package com.amc.acieslinski.simplegiftapp

val configuration = Configuration()
data class Configuration(
    val useFakeAccountLocalService: Boolean = false,
    val useFakeAccountRemoteService: Boolean = false,
    val useFakeDrawingUserRepository: Boolean = false,
)