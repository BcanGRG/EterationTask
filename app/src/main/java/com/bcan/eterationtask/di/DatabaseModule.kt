package com.bcan.eterationtask.di

import android.content.Context
import androidx.room.Room
import com.bcan.eterationtask.data.local.FavoriteProductDao
import com.bcan.eterationtask.data.local.ProductDao
import com.bcan.eterationtask.data.local.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            "product_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideProductDao(database: ProductDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    fun provideFavoriteProductDao(database: ProductDatabase): FavoriteProductDao {
        return database.favoriteProductDao()
    }
}