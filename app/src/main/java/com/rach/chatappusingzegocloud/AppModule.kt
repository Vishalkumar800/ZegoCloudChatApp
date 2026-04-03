package com.rach.chatappusingzegocloud

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
    fun provideZegoConfig(): ZegoConfig{
        return ZegoConfig(
            appId = ,
            appSign = ""
        )
    }

}

