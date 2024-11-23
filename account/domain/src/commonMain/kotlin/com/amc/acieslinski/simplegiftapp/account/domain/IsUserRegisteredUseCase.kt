package com.amc.acieslinski.simplegiftapp.account.domain

import com.amc.acieslinski.simplegiftapp.account.domain.repositories.AccountRepository

class IsUserRegisteredUseCase(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(): Boolean = accountRepository.isRegistered()
}