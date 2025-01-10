package com.amc.acieslinski.simplegiftapp.registration.di

import com.amc.acieslinski.simplegiftapp.configuration
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.AccountLocalFakeDataSource
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.AccountRemoteFakeDataSource
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.AccountRemoteLiveDataSource
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.AccountRemoteDataSource
import com.amc.acieslinski.simplegiftapp.registration.data.repository.account.AccountLiveRepository
import com.amc.acieslinski.simplegiftapp.db.databaseModule
import com.amc.acieslinski.simplegiftapp.di.networkModule
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.AccountLocalDataSource
import com.amc.acieslinski.simplegiftapp.registration.data.datasource.account.AccountLocalLiveDataSource
import com.amc.acieslinski.simplegiftapp.registration.domain.IsUserRegisteredUseCase
import com.amc.acieslinski.simplegiftapp.registration.domain.RegisterUseCase
import com.amc.acieslinski.simplegiftapp.registration.domain.repositories.AccountRepository
import org.koin.dsl.module

val commonRegistrationDataModule = databaseModule + networkModule + module {
    // data sources
    single<AccountLocalDataSource> {
        if (configuration.useFakeAccountLocalDataSource) {
            AccountLocalFakeDataSource()
        } else {
            AccountLocalLiveDataSource(get())
        }
    }
    single<AccountRemoteDataSource> {
        if (configuration.useFakeAccountRemoteDataSource) {
            AccountRemoteFakeDataSource()
        } else {
            AccountRemoteLiveDataSource(get())
        }
    }
    // repositories
    single<AccountRepository> { AccountLiveRepository(get(), get()) }
}

val commonRegistrationDomainModule = module {
    single<RegisterUseCase> { RegisterUseCase(get()) }
    single<IsUserRegisteredUseCase> { IsUserRegisteredUseCase(get()) }
}

val registrationModule = commonRegistrationDataModule + commonRegistrationDomainModule +
        platformRegistrationUiModule