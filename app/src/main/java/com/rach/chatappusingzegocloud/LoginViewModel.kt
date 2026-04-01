package com.rach.chatappusingzegocloud

import androidx.lifecycle.ViewModel
import com.zegocloud.zimkit.services.ZIMKit
import dagger.hilt.android.lifecycle.HiltViewModel
import im.zego.zim.entity.ZIMError
import im.zego.zim.enums.ZIMErrorCode
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor()  : ViewModel() {

    fun loginUser(userId: String , userName: String , onSuccess : () -> Unit , onError:(String) -> Unit){
        if (userId.isBlank() || userName.isBlank()){
            onError("User ID and UserName Required")
            return
        }

        val avatarUrl = "https://i.pravatar.cc/300?u=${userId}"

        ZIMKit.connectUser(userId,userName, avatarUrl){errorInfo ->
            if (errorInfo.code == ZIMErrorCode.SUCCESS){
                onSuccess()
            }else{
                onError(errorInfo.message ?: "Login Failed")
            }
        }
    }
}