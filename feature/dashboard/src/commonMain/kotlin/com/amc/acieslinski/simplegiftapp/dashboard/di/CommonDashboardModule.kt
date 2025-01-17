package com.amc.acieslinski.simplegiftapp.dashboard.di
import com.amc.acieslinski.simplegiftapp.configuration
import com.amc.acieslinski.simplegiftapp.dashboard.data.datasource.qrcode.QrCodeLocalDataSource
import com.amc.acieslinski.simplegiftapp.dashboard.data.datasource.qrcode.QrCodeLocalLiveDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.DrawingRemoteDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.DrawingRemoteLiveDataSource
import com.amc.acieslinski.simplegiftapp.dashboard.data.repository.drawing.DrawingLiveRepository
import com.amc.acieslinski.simplegiftapp.dashboard.data.repository.drawing.DrawingFakeRepository
import com.amc.acieslinski.simplegiftapp.dashboard.data.repository.user.UserLiveRepository
import com.amc.acieslinski.simplegiftapp.dashboard.domain.GetCurrentUserPublicQrCode
import com.amc.acieslinski.simplegiftapp.dashboard.domain.GetDrawingsUseCase
import com.amc.acieslinski.simplegiftapp.dashboard.domain.SelectDrawingUseCase
import com.amc.acieslinski.simplegiftapp.dashboard.domain.repositories.DrawingRepository
import com.amc.acieslinski.simplegiftapp.dashboard.domain.repositories.UserRepository
import com.amc.acieslinski.simplegiftapp.data.coreDataModule
import com.amc.acieslinski.simplegiftapp.data.store.credential.FakeCredentialStore
import com.amc.acieslinski.simplegiftapp.data.store.credential.CredentialStore
import com.amc.acieslinski.simplegiftapp.data.store.credential.LiveCredentialStore
import com.amc.acieslinski.simplegiftapp.db.databaseModule
import com.amc.acieslinski.simplegiftapp.di.networkModule
import org.koin.dsl.module

val commonDashboardDataModule = coreDataModule + module {
    // data sources
    single<QrCodeLocalDataSource> {
        QrCodeLocalLiveDataSource()
    }
    // repositories
    single<DrawingRepository> {
        if (configuration.useFakeDrawingRepository) {
            DrawingFakeRepository()
        } else {
            DrawingLiveRepository(get(), get())
        }
    }
    single<UserRepository> {
        UserLiveRepository(get(), get())
    }
}
val commonDashboardDomainModule = module {
    single<GetDrawingsUseCase> { GetDrawingsUseCase(get()) }
    single<SelectDrawingUseCase> { SelectDrawingUseCase(get()) }
    single<GetCurrentUserPublicQrCode> { GetCurrentUserPublicQrCode(get()) }
}
val dashboardModule = commonDashboardDataModule + commonDashboardDomainModule + platformDashboardUiModule