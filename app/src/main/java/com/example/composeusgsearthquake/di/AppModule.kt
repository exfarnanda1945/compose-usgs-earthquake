package com.example.composeusgsearthquake.di

import com.example.composeusgsearthquake.data.api.QuakeApi
import com.example.composeusgsearthquake.data.repository.QuakeRepository
import com.example.composeusgsearthquake.domain.repository.IQuakeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideQuakeRepository(api:QuakeApi):IQuakeRepository = QuakeRepository(api)
}