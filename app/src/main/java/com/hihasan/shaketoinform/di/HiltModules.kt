package com.hihasan.shaketoinform.di

import com.hihasan.shaketoinform.utils.App
import com.hihasan.shaketoinform.utils.BaseDatabase
import com.hihasan.shaketoinform.views.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class HiltModules {

    @Provides
    fun provideMainRepository() : MainRepository? {
        val bugDaoInstance = BaseDatabase.getDatabase(ctx = App.getAppContext()!!).bugDao

        return MainRepository(bugDaoInstance)
    }

}