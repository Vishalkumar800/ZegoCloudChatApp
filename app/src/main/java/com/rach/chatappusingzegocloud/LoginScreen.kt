package com.rach.chatappusingzegocloud

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {

    var userName by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Login",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = userName,
            onValueChange = {
                userName = it
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text(text = "Enter Username")
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading,
            onClick = {
                isLoading = true
                errorMessage = ""

                viewModel.loginUser(
                    userId = userName.trim(),
                    userName = userName.trim(),
                    onSuccess = {
                        isLoading = false
                        onLoginSuccess()
                    },
                    onError = {
                        isLoading = false
                        errorMessage = it
                    }
                )
            }
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Text(text = "Login To Chat")
            }
        }
    }
}