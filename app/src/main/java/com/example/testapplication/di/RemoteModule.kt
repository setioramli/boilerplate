package com.example.testapplication.di

import com.example.testapplication.domain.remote.UserListDataSource
import com.example.testapplication.domain.remote.UserListService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class RemoteModule {

    @Provides
    @ViewModelScoped
    fun provideUserListService(retrofit: Retrofit) = retrofit.create(UserListService::class.java)

    @Provides
    @ViewModelScoped
    fun provideUserListDataSource(userListService: UserListService) = UserListDataSource(userListService)
}