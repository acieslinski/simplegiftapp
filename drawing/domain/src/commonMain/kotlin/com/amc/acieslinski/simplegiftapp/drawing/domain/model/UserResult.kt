package com.amc.acieslinski.simplegiftapp.drawing.domain.model

sealed class UserResult(
    val isSuccessful: Boolean = false
) {
    data class Success(
        val user: User
    ): UserResult(isSuccessful = true)

    data object NotFound: UserResult()

    data object UnknownIssue: UserResult()
}