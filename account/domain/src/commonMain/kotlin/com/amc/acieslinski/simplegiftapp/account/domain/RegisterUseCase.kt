package com.amc.acieslinski.simplegiftapp.account.domain

import com.amc.acieslinski.simplegiftapp.account.domain.repositories.AccountRepository

class RegisterUseCase(
    private val accountRepository: AccountRepository
) {
    operator fun invoke(name: String, surname: String) = accountRepository.register(name, surname)
}