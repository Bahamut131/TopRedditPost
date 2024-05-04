package com.example.topredditpost.di

import com.example.topredditpost.data.network.ApiFactory
import com.example.topredditpost.data.network.ApiService
import com.example.topredditpost.data.repository.TopPostRepositoryImpl
import com.example.topredditpost.domain.repository.TopPostRepository
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindsRepository(impl: TopPostRepositoryImpl) : TopPostRepository

    companion object{
        @Provides
        @ApplicationScope
        fun provideApiService() : ApiService{
            return ApiFactory.apiService
        }
    }

}