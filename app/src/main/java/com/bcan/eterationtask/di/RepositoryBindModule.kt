package com.bcan.eterationtask.di

import com.bcan.eterationtask.data.repository.ProductsRepository
import com.bcan.eterationtask.data.repository.ProductsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryBindModule {

    @Binds
    abstract fun bindProductsRepository(
        repositoryImpl: ProductsRepositoryImpl,
    ): ProductsRepository

}