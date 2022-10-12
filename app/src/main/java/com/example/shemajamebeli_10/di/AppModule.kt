package com.example.shemajamebeli_10.di

import android.content.Context
import com.example.shemajamebeli_10.data.remote.Client
import com.example.shemajamebeli_10.data.remote.MessagesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
        return  Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .client(Client.getInstance(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMyApi(retrofit: Retrofit): MessagesApi {
        return retrofit.create(MessagesApi::class.java)
    }

}