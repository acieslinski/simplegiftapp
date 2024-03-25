package com.amc.acieslinski.simplegiftapp.drawing.di

import com.amc.acieslinski.simplegiftapp.account.datasource.AccountLocalFakeService
import com.amc.acieslinski.simplegiftapp.account.datasource.AccountLocalService
import com.amc.acieslinski.simplegiftapp.account.datasource.AccountRemoteFakeService
import com.amc.acieslinski.simplegiftapp.account.datasource.AccountRemoteService
import com.amc.acieslinski.simplegiftapp.account.datasource.mapper.AccountDataMapper
import com.amc.acieslinski.simplegiftapp.account.domain.RegisterUseCase
import com.amc.acieslinski.simplegiftapp.account.domain.repositories.AccountRepository
import com.amc.acieslinski.simplegiftapp.account.presentation.RegistrationViewModel
import com.amc.acieslinski.simplegiftapp.account.repository.AccountLocalDataSource
import com.amc.acieslinski.simplegiftapp.account.repository.AccountRemoteDataSource
import com.amc.acieslinski.simplegiftapp.account.repository.AccountRepositoryImpl
import com.amc.acieslinski.simplegiftapp.configuration
import com.amc.acieslinski.simplegiftapp.db.databaseModule
import com.amc.acieslinski.simplegiftapp.di.networkModule
import org.koin.dsl.module

actual val accountModule = databaseModule + networkModule + module {
    single<AccountDataMapper> { AccountDataMapper() }
    single<AccountLocalDataSource> {
        if (configuration.useFakeAccountLocalService) {
            AccountLocalFakeService()
        } else {
            AccountLocalService(get(), get())
        }
    }
    single<AccountRemoteDataSource> {
        if (configuration.useFakeAccountRemoteService) {
            AccountRemoteFakeService()
        } else {
            AccountRemoteService(get(), get())
        }
    }
    single<RegisterUseCase> { RegisterUseCase(get()) }
    single<AccountRepository> { AccountRepositoryImpl(get(), get()) }
    single<RegistrationViewModel> { RegistrationViewModel(get()) }
}