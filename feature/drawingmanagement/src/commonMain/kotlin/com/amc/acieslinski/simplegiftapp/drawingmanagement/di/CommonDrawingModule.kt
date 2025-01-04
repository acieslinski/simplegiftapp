package com.amc.acieslinski.simplegiftapp.drawingmanagement.di
import com.amc.acieslinski.simplegiftapp.configuration
import com.amc.acieslinski.simplegiftapp.data.datasource.token.FakePrivateTokenLocalDataSourceImpl
import com.amc.acieslinski.simplegiftapp.data.datasource.token.PrivateTokenDataSource
import com.amc.acieslinski.simplegiftapp.data.datasource.token.PrivateTokenLocalDataSourceImpl
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.drawing.DrawingRemoteDataSource
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.drawing.DrawingRemoteDataSourceImpl
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.drawing.mapper.DrawingRemoteMapper
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.user.UserRemoteDataSource
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.user.UserRemoteDataSourceImpl
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.datasource.user.mapper.UserRemoteMapper
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing.DrawingRepositoryImpl
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing.FakeDrawingRepositoryImpl
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.drawing.mapper.DrawingDataMapper
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.user.UserFakeRepositoryImpl
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.user.UserRepositoryImpl
import com.amc.acieslinski.simplegiftapp.drawingmanagement.data.repository.user.mappers.UserDataMapper
import com.amc.acieslinski.simplegiftapp.db.databaseModule
import com.amc.acieslinski.simplegiftapp.di.networkModule
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.CreateDrawingUseCase
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.GetSelectedDrawingUseCase
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.GetUserUseCase
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.DrawingRepository
import com.amc.acieslinski.simplegiftapp.drawingmanagement.domain.repositories.UserRepository
import org.koin.dsl.module

val commonDrawingDataModule = databaseModule + networkModule + module {
    // data sources
    single<UserRemoteMapper> { UserRemoteMapper() }
    single<DrawingRemoteMapper> { DrawingRemoteMapper() }
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get(), get()) }
    single<DrawingRemoteDataSource> { DrawingRemoteDataSourceImpl(get(), get()) }
    single<PrivateTokenDataSource> {
        if (configuration.useFakePrivateTokenLocalDataSource) {
            FakePrivateTokenLocalDataSourceImpl()
        } else {
            PrivateTokenLocalDataSourceImpl(get())
        }
    }
    // repositories
    single<UserDataMapper> { UserDataMapper() }
    single<DrawingDataMapper> { DrawingDataMapper() }
    single<UserRepository> {
        if (configuration.useFakeDrawingUserRepository) {
            UserFakeRepositoryImpl()
        } else {
            UserRepositoryImpl(get(), get())
        }
    }
    single<DrawingRepository> {
        if (configuration.useFakeDrawingRepository) {
            FakeDrawingRepositoryImpl()
        } else {
            DrawingRepositoryImpl(get(), get(), get(), get())
        }
    }
}
val commonDrawingDomainModule = module {
    single<GetUserUseCase> { GetUserUseCase(get()) }
    single<GetSelectedDrawingUseCase> { GetSelectedDrawingUseCase(get()) }
    single<CreateDrawingUseCase> { CreateDrawingUseCase(get()) }
}
val drawingManagementModule = commonDrawingDataModule + commonDrawingDomainModule + platformDrawingUiModule