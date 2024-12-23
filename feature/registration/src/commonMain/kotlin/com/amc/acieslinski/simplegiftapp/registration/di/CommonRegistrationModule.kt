package com.amc.acieslinski.simplegiftapp.registration.di

import com.amc.acieslinski.simplegiftapp.configuration
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.AccountLocalFakeDataSource
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.AccountRemoteFakeDataSource
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.AccountRemoteDataSourceImpl
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.mapper.AccountDataMapper
import com.amc.acieslinski.simplegiftapp.registration.data.repository.account.AccountRemoteDataSource
import com.amc.acieslinski.simplegiftapp.registration.data.repository.account.AccountRepositoryImpl
import com.amc.acieslinski.simplegiftapp.db.databaseModule
import com.amc.acieslinski.simplegiftapp.di.networkModule
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.AccountLocalDataSource
import com.amc.acieslinski.simplegiftapp.registration.domain.IsUserRegisteredUseCase
import com.amc.acieslinski.simplegiftapp.registration.domain.RegisterUseCase
import com.amc.acieslinski.simplegiftapp.registration.domain.repositories.AccountRepository
import org.koin.dsl.module

val commonRegistrationDataModule = databaseModule + networkModule + module {
    // data sources
    single<AccountDataMapper> { AccountDataMapper() }
    single<com.amc.acieslinski.simplegiftapp.registration.data.repository.account.AccountLocalDataSource> {
        if (configuration.useFakeAccountLocalDataSource) {
            AccountLocalFakeDataSource()
        } else {
            AccountLocalDataSource(get(), get())
        }
    }
    single<AccountRemoteDataSource> {
        if (configuration.useFakeAccountRemoteDataSource) {
            AccountRemoteFakeDataSource()
        } else {
            AccountRemoteDataSourceImpl(get(), get())
        }
    }
    // repositories
    single<AccountRepository> { AccountRepositoryImpl(get(), get()) }
}

val commonRegistrationDomainModule = module {
    single<RegisterUseCase> { RegisterUseCase(get()) }
    single<IsUserRegisteredUseCase> { IsUserRegisteredUseCase(get()) }
}

val registrationModule = commonRegistrationDataModule + commonRegistrationDomainModule +
        platformRegistrationUiModule