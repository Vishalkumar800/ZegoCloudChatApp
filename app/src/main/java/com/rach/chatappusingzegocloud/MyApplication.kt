package com.rach.chatappusingzegocloud

import android.app.Application
import com.zegocloud.zimkit.services.ZIMKit
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

data class ZegoConfig(
    val appId: Long,
    val appSign: String
)

@HiltAndroidApp
class MyApplication: Application() {

    @Inject
    lateinit var zegoConfig: ZegoConfig

    override fun onCreate() {
        super.onCreate()

//        ZIMKit.initWith(this) {
//            appID = 169005112
//            appSign = "205dceb35039908caae6d61708dd3b384e3040c8d10d3489602ed02540cbdd6c"
//        }

        ZIMKit.initWith(this, 2 ,  "0c8d10d3489602ed02540cbdd6c")

    }

}
