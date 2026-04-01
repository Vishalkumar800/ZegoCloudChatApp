package com.rach.chatappusingzegocloud

import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import com.zegocloud.zimkit.common.ZIMKitRouter
import com.zegocloud.zimkit.common.enums.ZIMKitConversationType
import com.zegocloud.zimkit.components.conversation.ui.ZIMKitConversationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ZegoCloudChatAct : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ConversationScreen()
        }
    }
}

@Composable
fun ConversationScreen() {

    val context = LocalContext.current
    val activity = context as FragmentActivity
    val fragmentId = remember { View.generateViewId() }

    var showDialog by remember { mutableStateOf(false) }
    var targetUserId by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {

        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                FragmentContainerView(context).apply {
                    id = fragmentId

                    activity.supportFragmentManager.beginTransaction()
                        .replace(id, ZIMKitConversationFragment())
                        .commit()
                }
            }
        )

        FloatingActionButton(
            onClick = {
                showDialog = true
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Start Chat"
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                title = {
                    Text(text = "Start New Chat")
                },
                text = {
                    OutlinedTextField(
                        value = targetUserId,
                        onValueChange = {
                            targetUserId = it
                        },
                        singleLine = true,
                        label = {
                            Text(text = "Enter User ID")
                        }
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            if (targetUserId.isNotBlank()) {
                                ZIMKitRouter.toMessageActivity(
                                    context,
                                    targetUserId.trim(),
                                    ZIMKitConversationType.ZIMKitConversationTypePeer
                                )
                                showDialog = false
                            }
                        }
                    ) {
                        Text(text = "Chat")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text(text = "Cancel")
                    }
                }
            )
        }
    }
}