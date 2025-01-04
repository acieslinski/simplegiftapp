package com.amc.acieslinski.simplegiftapp.dashboard.di
import com.amc.acieslinski.simplegiftapp.configuration
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.DrawingRemoteDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.drawing.DrawingRemoteDataSourceImpl
import com.amc.acieslinski.simplegiftapp.dashboard.data.repository.drawing.DrawingRepositoryImpl
import com.amc.acieslinski.simplegiftapp.dashboard.data.repository.drawing.FakeDrawingRepositoryImpl
import com.amc.acieslinski.simplegiftapp.dashboard.data.repository.drawing.mapper.DrawingDataMapper
import com.amc.acieslinski.simplegiftapp.dashboard.domain.GetDrawingsUseCase
import com.amc.acieslinski.simplegiftapp.dashboard.domain.SelectDrawingUseCase
import com.amc.acieslinski.simplegiftapp.dashboard.domain.repositories.DrawingRepository
import com.amc.acieslinski.simplegiftapp.data.datasource.token.FakePrivateTokenLocalDataSourceImpl
import com.amc.acieslinski.simplegiftapp.data.datasource.token.PrivateTokenDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.token.PrivateTokenLocalDataSourceImpl
import com.amc.acieslinski.simplegiftapp.db.databaseModule
import com.amc.acieslinski.simplegiftapp.di.networkModule
import org.koin.dsl.module

val commonDashboardDataModule = databaseModule + networkModule + module {
    // data sources
    single<DrawingRemoteDataSource> { DrawingRemoteDataSourceImpl(get()) }
    single<PrivateTokenDataSource> {
        if (configuration.useFakePrivateTokenLocalDataSource) {
            FakePrivateTokenLocalDataSourceImpl()
        } else {
            PrivateTokenLocalDataSourceImpl(get())
        }
    }
    // repositories
    single<DrawingDataMapper> { DrawingDataMapper() }
    single<DrawingRepository> {
        if (configuration.useFakeDrawingRepository) {
            FakeDrawingRepositoryImpl()
        } else {
            DrawingRepositoryImpl(get(), get(), get())
        }
    }
}
val commonDashboardDomainModule = module {
    single<GetDrawingsUseCase> { GetDrawingsUseCase(get()) }
    single<SelectDrawingUseCase> { SelectDrawingUseCase(get()) }
}
val dashboardModule = commonDashboardDataModule + commonDashboardDomainModule + platformDashboardUiModule