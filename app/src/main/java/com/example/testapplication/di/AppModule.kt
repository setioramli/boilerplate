package com.example.testapplication.di

import android.content.Context
import com.example.testapplication.BuildConfig
import com.example.testapplication.config.RemoteConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule{

    @Provides
    @Singleton
    fun provideService(@ApplicationContext context: Context) = RemoteConfig.serviceBuilder(
        context,
        BuildConfig.BASE_URL
    )


}