package com.example.testapplication.di

import com.example.testapplication.domain.remote.UserListDataSource
import com.example.testapplication.domain.repositories.UserListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)

class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideUserListRepository(userListDataSource: UserListDataSource) = UserListRepository(userListDataSource)
}