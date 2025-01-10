package com.amc.acieslinski.simplegiftapp.drawingmanagement.di
import com.amc.acieslinski.simplegiftapp.configuration
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.DrawingRemoteDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.DrawingRemoteLiveDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.SelectedDrawingIdLocalDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.SelectedDrawingIdLocalLiveDataSource
import com.amc.acieslinski.simplegiftapp.data.store.credential.FakeCredentialStore
import com.amc.acieslinski.simplegiftapp.data.store.credential.CredentialStore
import com.amc.acieslinski.simplegiftapp.data.store.credential.LiveCredentialStore
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.user.UserRemoteDataSource
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.user.UserRemoteLiveDataSource
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing.DrawingLiveRepository
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing.DrawingFakeRepository
import com.amc.acieslinski.simplegiftapp.db.databaseModule
import com.amc.acieslinski.simplegiftapp.di.networkModule
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.AddParticipantToSelectedDrawingUseCase
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.CreateDrawingUseCase
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.ObserveSelectedDrawingUseCase
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository
import org.koin.dsl.module

val commonDrawingDataModule = databaseModule + networkModule + module {
    // stores
    single<CredentialStore> {
        if (configuration.useFakeCredentialStore) {
            FakeCredentialStore()
        } else {
            LiveCredentialStore(get())
        }
    }
    // data sources
    single<UserRemoteDataSource> { UserRemoteLiveDataSource(get()) }
    single<DrawingRemoteDataSource> { DrawingRemoteLiveDataSource(get(), get()) }
    single<SelectedDrawingIdLocalDataSource> { SelectedDrawingIdLocalLiveDataSource() }
    // repositories
    single<DrawingRepository> {
        if (configuration.useFakeDrawingRepository) {
            DrawingFakeRepository()
        } else {
            DrawingLiveRepository(get(), get(), get())
        }
    }
}
val commonDrawingDomainModule = module {
    single<ObserveSelectedDrawingUseCase> { ObserveSelectedDrawingUseCase(get()) }
    single<AddParticipantToSelectedDrawingUseCase> { AddParticipantToSelectedDrawingUseCase(get()) }
    single<CreateDrawingUseCase> { CreateDrawingUseCase(get()) }
}
val drawingManagementModule = commonDrawingDataModule + commonDrawingDomainModule + platformDrawingUiModule