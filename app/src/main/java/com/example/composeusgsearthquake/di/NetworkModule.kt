package com.example.composeusgsearthquake.di

import com.example.composeusgsearthquake.constant.RemoteSourceConstant
import com.example.composeusgsearthquake.data.api.QuakeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: GsonConverterFactory): Retrofit =
        Retrofit.Builder().baseUrl(RemoteSourceConstant.BASE_URL).addConverterFactory(gson).build()

    @Provides
    @Singleton
    fun provideQuakeApi(retrofit: Retrofit): QuakeApi = retrofit.create(QuakeApi::class.java)
}