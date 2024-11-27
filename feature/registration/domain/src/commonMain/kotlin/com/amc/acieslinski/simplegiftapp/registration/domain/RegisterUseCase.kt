package com.amc.acieslinski.simplegiftapp.registration.domain

import com.amc.acieslinski.simplegiftapp.registration.domain.repositories.AccountRepository

class RegisterUseCase(
    private val accountRepository: AccountRepository
) {
    operator fun invoke(name: String, surname: String) = accountRepository.register(name, surname)
}