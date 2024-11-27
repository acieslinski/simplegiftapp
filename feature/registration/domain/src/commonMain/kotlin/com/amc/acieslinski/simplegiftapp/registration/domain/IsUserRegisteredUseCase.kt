package com.amc.acieslinski.simplegiftapp.registration.domain

import com.amc.acieslinski.simplegiftapp.registration.domain.repositories.AccountRepository

class IsUserRegisteredUseCase(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(): Boolean = accountRepository.isRegistered()
}