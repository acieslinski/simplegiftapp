package com.amc.acieslinski.simplegiftapp.dashboard.di
import com.amc.acieslinski.simplegiftapp.configuration
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.DrawingRemoteDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.DrawingRemoteLiveDataSource
import com.amc.acieslinski.simplegiftapp.dashboard.data.repository.drawing.DrawingLiveRepository
import com.amc.acieslinski.simplegiftapp.dashboard.data.repository.drawing.DrawingFakeRepository
import com.amc.acieslinski.simplegiftapp.dashboard.domain.GetDrawingsUseCase
import com.amc.acieslinski.simplegiftapp.dashboard.domain.SelectDrawingUseCase
import com.amc.acieslinski.simplegiftapp.dashboard.domain.repositories.DrawingRepository
import com.amc.acieslinski.simplegiftapp.data.store.credential.FakeCredentialStore
import com.amc.acieslinski.simplegiftapp.data.store.credential.CredentialStore
import com.amc.acieslinski.simplegiftapp.data.store.credential.LiveCredentialStore
import com.amc.acieslinski.simplegiftapp.db.databaseModule
import com.amc.acieslinski.simplegiftapp.di.networkModule
import org.koin.dsl.module

val commonDashboardDataModule = databaseModule + networkModule + module {
    // stores
    single<CredentialStore> {
        if (configuration.useFakeCredentialStore) {
            FakeCredentialStore()
        } else {
            LiveCredentialStore(get())
        }
    }
    // data sources
    single<DrawingRemoteDataSource> { DrawingRemoteLiveDataSource(get(), get()) }
    // repositories
    single<DrawingRepository> {
        if (configuration.useFakeDrawingRepository) {
            DrawingFakeRepository()
        } else {
            DrawingLiveRepository(get(), get())
        }
    }
}
val commonDashboardDomainModule = module {
    single<GetDrawingsUseCase> { GetDrawingsUseCase(get()) }
    single<SelectDrawingUseCase> { SelectDrawingUseCase(get()) }
}
val dashboardModule = commonDashboardDataModule + commonDashboardDomainModule + platformDashboardUiModule