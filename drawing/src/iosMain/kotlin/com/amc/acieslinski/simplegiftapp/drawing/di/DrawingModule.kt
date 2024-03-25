package com.amc.acieslinski.simplegiftapp.drawing.di

import com.amc.acieslinski.simplegiftapp.configuration
import com.amc.acieslinski.simplegiftapp.db.databaseModule
import com.amc.acieslinski.simplegiftapp.di.networkModule
import com.amc.acieslinski.simplegiftapp.drawing.datasource.UserRemoteService
import com.amc.acieslinski.simplegiftapp.drawing.datasource.mapper.UserDataMapper
import com.amc.acieslinski.simplegiftapp.drawing.domain.GetUserUseCase
import com.amc.acieslinski.simplegiftapp.drawing.domain.repositories.UserRepository
import com.amc.acieslinski.simplegiftapp.drawing.presentation.DrawingViewModel
import com.amc.acieslinski.simplegiftapp.drawing.presentation.DrawingViewModelImpl
import com.amc.acieslinski.simplegiftapp.drawing.repository.UserFakeRepositoryImpl
import com.amc.acieslinski.simplegiftapp.drawing.repository.UserRemoteDataSource
import com.amc.acieslinski.simplegiftapp.drawing.repository.UserRepositoryImpl
import org.koin.dsl.module

actual val drawingModule = databaseModule + networkModule + module {
    single<UserDataMapper> { UserDataMapper() }
    single<UserRemoteDataSource> { UserRemoteService(get(), get()) }
    single<GetUserUseCase> { GetUserUseCase(get()) }
    single<UserRepository> {
        if (configuration.useFakeDrawingUserRepository) {
            UserFakeRepositoryImpl()
        } else {
            UserRepositoryImpl(get())
        }
    }
    single<DrawingViewModel> { DrawingViewModelImpl(get()) }
}