package com.example.shemajamebeli_10.di

import com.example.shemajamebeli_10.data.repository.MessagesRepositoryImpl
import com.example.shemajamebeli_10.domain.repository.MyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(
        myRepositoryImpl: MessagesRepositoryImpl
    ): MyRepository
}