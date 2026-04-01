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
            appId = 169005112,
            appSign = "205dceb35039908caae6d61708dd3b384e3040c8d10d3489602ed02540cbdd6c"
        )
    }

}

