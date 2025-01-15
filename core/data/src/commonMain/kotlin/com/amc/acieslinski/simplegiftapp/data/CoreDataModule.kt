package com.amc.acieslinski.simplegiftapp.data

import com.amc.acieslinski.simplegiftapp.configuration
import com.amc.acieslinski.simplegiftapp.data.datasource.account.AccountLocalDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.account.AccountLocalFakeDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.account.AccountLocalLiveDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.DrawingRemoteDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.DrawingRemoteLiveDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.SelectedDrawingIdLocalDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.SelectedDrawingIdLocalLiveDataSource
import com.amc.acieslinski.simplegiftapp.data.store.credential.CredentialStore
import com.amc.acieslinski.simplegiftapp.data.store.credential.FakeCredentialStore
import com.amc.acieslinski.simplegiftapp.data.store.credential.LiveCredentialStore
import com.amc.acieslinski.simplegiftapp.db.databaseModule
import com.amc.acieslinski.simplegiftapp.di.networkModule
import org.koin.dsl.module

val coreDataModule = databaseModule + networkModule + module {
    // stores
    single<CredentialStore> {
        if (configuration.useFakeCredentialStore) {
            FakeCredentialStore()
        } else {
            LiveCredentialStore(get())
        }
    }
    // data sources
    single<AccountLocalDataSource> {
        if (configuration.useFakeAccountLocalDataSource) {
            AccountLocalFakeDataSource()
        } else {
            AccountLocalLiveDataSource(get())
        }
    }
    single<SelectedDrawingIdLocalDataSource> { SelectedDrawingIdLocalLiveDataSource() }
    single<DrawingRemoteDataSource> { DrawingRemoteLiveDataSource(get(), get()) }
}