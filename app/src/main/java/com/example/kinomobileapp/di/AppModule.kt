package com.example.kinomobileapp.di

import com.example.kinomobileapp.data.api.PocketBaseApi
import com.example.kinomobileapp.data.api.RetrofitClient
import com.example.kinomobileapp.domain.repository.MovieRepository
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
    fun providePocketBaseApi() : PocketBaseApi {
        return RetrofitClient.pocketBaseApi
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: PocketBaseApi): MovieRepository {
        return MovieRepository(api)
    }
}