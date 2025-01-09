package com.bcan.eterationtask.di

import com.bcan.eterationtask.data.service.ProductsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideProductsService(retrofit: Retrofit): ProductsService =
        retrofit.create(ProductsService::class.java)

}