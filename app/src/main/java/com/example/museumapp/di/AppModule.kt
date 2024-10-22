package com.example.museumapp.di

import com.example.museumapp.data.repository.MuseumRepository
import com.example.museumapp.data.services.MuseumService
import com.example.museumapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesMuseumService(retrofit: Retrofit): MuseumService {
            return retrofit.create(MuseumService::class.java)
    }

    @Provides
    @Singleton
    fun provideMuseumRepository(museumService: MuseumService) : MuseumRepository {
        return MuseumRepository(museumService)
    }
}